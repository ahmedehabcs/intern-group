package com.talabaty.backend.config;

import com.talabaty.backend.model.Role;
import com.talabaty.backend.model.User;
import com.talabaty.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    @Value("${admin.seed.email:admin@talabaty.com}")
    private String adminEmail;

    @Value("${admin.seed.password:ChangeThisPassword123!}")
    private String adminPassword;

    @Bean
    public CommandLineRunner seedAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            boolean adminExists = userRepository.findByEmail(adminEmail).isPresent();

            if (!adminExists) {
                User admin = new User(
                        adminEmail,
                        passwordEncoder.encode(adminPassword),
                        Role.ADMIN
                );
                admin.setEmailVerified(true); // skip OTP flow entirely for the seeded admin
                userRepository.save(admin);
                System.out.println("Default admin account created: " + adminEmail);
            } else {
                System.out.println("Admin account already exists, skipping seed.");
            }
        };
    }
}
