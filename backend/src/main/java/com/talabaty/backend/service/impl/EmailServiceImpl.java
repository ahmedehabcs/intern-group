package com.talabaty.backend.service.impl;

import com.talabaty.backend.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;

    /**
     * Whether one-time passwords are delivered by e-mail.
     * <p>
     * When false, they are written to the log instead of sent. That is what
     * makes signup work on a machine with no SMTP credentials: the account is
     * still created and still needs verifying, but the code needed to verify it
     * is on the console instead of in an inbox.
     * <p>
     * The flag is applied here rather than at the six call sites in
     * {@code AuthServiceImpl}, so signup, resend, forgot-password, and the
     * e-mail/password change flows all honour it without anyone remembering to
     * check. {@code otp.enabled} was previously read only by {@code OtpService},
     * which nothing references - so setting OTP_ENABLED=false changed nothing
     * and registering a user failed with "failed to connect, no password
     * specified?" from Jakarta Mail.
     */
    private final boolean otpEnabled;

    /**
     * Whether an SMTP username is configured.
     * <p>
     * Without one there is no credential to authenticate with, so a send cannot
     * possibly succeed: it fails as "failed to connect, no password specified?"
     * and takes the whole registration down with it. Treating that as "delivery
     * is off" rather than attempting it anyway is what lets a machine with no
     * mail setup register a user, without depending on OTP_ENABLED having been
     * set correctly first.
     */
    private final boolean mailConfigured;

    public EmailServiceImpl(
            JavaMailSender mailSender,
            @Value("${otp.enabled:true}") boolean otpEnabled,
            @Value("${spring.mail.username:}") String mailUsername
    ) {
        this.mailSender = mailSender;
        this.otpEnabled = otpEnabled;
        this.mailConfigured = !mailUsername.isBlank();

        if (otpEnabled && !mailConfigured) {
            // Surfaced at startup rather than at the first signup. A deployment
            // that intends to send mail but is missing MAIL_USERNAME is
            // misconfigured, and that belongs in the boot log instead of
            // waiting for a real user to register and silently receive nothing.
            log.warn(
                    "otp.enabled=true but spring.mail.username is empty, so no e-mail can be sent. "
                            + "One-time passwords will be written to this log instead. "
                            + "Set MAIL_USERNAME and MAIL_PASSWORD to deliver them for real."
            );
        }
    }

    @Override
    public void sendOtpEmail(String to, String otp) {
        send(to, "Your OTP for Talabaty", "Your OTP is: " + otp, otp);
    }

    @Override
    public void sendPasswordResetEmail(String to, String otp) {
        send(to, "Password Reset Request", "Your password reset OTP is: " + otp, otp);
    }

    private void send(String to, String subject, String body, String otp) {
        if (!otpEnabled || !mailConfigured) {
            // WARN, not DEBUG: this prints a live credential, and seeing it in
            // the log should be a reminder that mail is not going out. Neither
            // branch is reachable in a correctly configured deployment, which
            // leaves otp.enabled at its default of true and sets MAIL_USERNAME.
            log.warn(
                    "{}, so \"{}\" was not sent to {}. Use this code to continue: {}",
                    otpEnabled ? "No SMTP username is configured" : "otp.enabled=false",
                    subject, to, otp
            );
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
