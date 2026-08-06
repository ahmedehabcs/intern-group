from reportlab.lib import colors
from reportlab.lib.enums import TA_CENTER
from reportlab.lib.pagesizes import A4
from reportlab.lib.styles import ParagraphStyle, getSampleStyleSheet
from reportlab.lib.units import mm
from reportlab.platypus import (
    SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle,
    PageBreak, KeepTogether
)


OUTPUT = "output/pdf/authentication-module-documentation.pdf"


def footer(canvas, doc):
    canvas.saveState()
    canvas.setStrokeColor(colors.HexColor("#D7E0EA"))
    canvas.line(doc.leftMargin, 13 * mm, A4[0] - doc.rightMargin, 13 * mm)
    canvas.setFillColor(colors.HexColor("#64748B"))
    canvas.setFont("Helvetica", 8)
    canvas.drawString(doc.leftMargin, 8 * mm, "Talabaty Backend - Authentication Module")
    canvas.drawRightString(A4[0] - doc.rightMargin, 8 * mm, f"Page {doc.page}")
    canvas.restoreState()


styles = getSampleStyleSheet()
styles.add(ParagraphStyle(
    name="TitleCustom", parent=styles["Title"], fontName="Helvetica-Bold",
    fontSize=24, leading=29, textColor=colors.HexColor("#0F172A"),
    alignment=TA_CENTER, spaceAfter=8
))
styles.add(ParagraphStyle(
    name="Subtitle", parent=styles["Normal"], fontName="Helvetica",
    fontSize=11, leading=15, textColor=colors.HexColor("#475569"),
    alignment=TA_CENTER, spaceAfter=24
))
styles.add(ParagraphStyle(
    name="H1Custom", parent=styles["Heading1"], fontName="Helvetica-Bold",
    fontSize=16, leading=20, textColor=colors.HexColor("#0F3D5E"),
    spaceBefore=16, spaceAfter=8
))
styles.add(ParagraphStyle(
    name="H2Custom", parent=styles["Heading2"], fontName="Helvetica-Bold",
    fontSize=12, leading=15, textColor=colors.HexColor("#174E72"),
    spaceBefore=10, spaceAfter=5
))
styles.add(ParagraphStyle(
    name="BodyCustom", parent=styles["BodyText"], fontName="Helvetica",
    fontSize=9.5, leading=14, textColor=colors.HexColor("#1E293B"),
    spaceAfter=6
))
styles.add(ParagraphStyle(
    name="CodeCustom", parent=styles["Code"], fontName="Courier", fontSize=8.4,
    leading=12, textColor=colors.HexColor("#0F172A"), backColor=colors.HexColor("#F1F5F9"),
    borderColor=colors.HexColor("#D7E0EA"), borderWidth=0.5, borderPadding=7,
    spaceBefore=4, spaceAfter=9
))
styles.add(ParagraphStyle(
    name="Small", parent=styles["BodyText"], fontName="Helvetica",
    fontSize=8.5, leading=11, textColor=colors.HexColor("#334155")
))
styles.add(ParagraphStyle(
    name="TableHeader", parent=styles["BodyText"], fontName="Helvetica-Bold",
    fontSize=8.5, leading=11, textColor=colors.white
))


def p(text, style="BodyCustom"):
    return Paragraph(text, styles[style])


def code(text):
    return p(text.replace("\n", "<br/>"), "CodeCustom")


def table(rows, widths, header=True):
    converted = []
    for row_index, row in enumerate(rows):
        row_style = "TableHeader" if header and row_index == 0 else "Small"
        converted.append([p(cell, row_style) for cell in row])
    t = Table(converted, colWidths=widths, repeatRows=1 if header else 0, hAlign="LEFT")
    commands = [
        ("GRID", (0, 0), (-1, -1), 0.35, colors.HexColor("#CBD5E1")),
        ("VALIGN", (0, 0), (-1, -1), "TOP"),
        ("LEFTPADDING", (0, 0), (-1, -1), 7),
        ("RIGHTPADDING", (0, 0), (-1, -1), 7),
        ("TOPPADDING", (0, 0), (-1, -1), 6),
        ("BOTTOMPADDING", (0, 0), (-1, -1), 6),
    ]
    if header:
        commands += [
            ("BACKGROUND", (0, 0), (-1, 0), colors.HexColor("#0F3D5E")),
            ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
        ]
    t.setStyle(TableStyle(commands))
    return t


story = []
story.append(Spacer(1, 18 * mm))
story.append(p("Authentication Module", "TitleCustom"))
story.append(p("Talabaty Spring Boot Backend - Implementation and API Reference", "Subtitle"))

summary_rows = [
    ["Capability", "Implemented behavior"],
    ["Credentials", "Email and BCrypt-verified password"],
    ["Email verification", "Login is rejected when emailVerified is false"],
    ["Tokens", "JWT access token only - no refresh tokens"],
    ["JWT claims", "userId, email, role"],
    ["Rate limit", "5 failed attempts per email-and-IP key within 15 minutes"],
]
story.append(table(summary_rows, [47 * mm, 113 * mm]))
story.append(Spacer(1, 8 * mm))
story.append(p("Module scope", "H1Custom"))
story.append(p(
    "This module authenticates a user with email and password, verifies the stored BCrypt hash, "
    "requires a verified email address, and issues a signed access JWT. It uses stateless Spring Security."
))
story.append(p("Key security decisions", "H2Custom"))
story.append(p(
    "Only access tokens are issued. HTTP Basic authentication, form login, and server sessions are disabled. "
    "A valid Bearer token is required for all endpoints except authentication and Swagger routes."
))
story.append(PageBreak())

story.append(p("Login API", "H1Custom"))
story.append(p("Endpoint", "H2Custom"))
story.append(code("POST /api/auth/login"))
story.append(p("Request body", "H2Custom"))
story.append(code('{<br/>  "email": "customer@example.com",<br/>  "password": "TestPassword123!"<br/>}'))
story.append(p("Validation", "H2Custom"))
story.append(table([
    ["Field", "Rules"],
    ["email", "Required and must use a valid email format"],
    ["password", "Required and cannot be blank"],
], [42 * mm, 118 * mm]))
story.append(Spacer(1, 5 * mm))
story.append(p("Successful response - 200 OK", "H2Custom"))
story.append(code('{<br/>  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",<br/>  "tokenType": "Bearer",<br/>  "expiresIn": 900,<br/>  "userId": 1,<br/>  "email": "customer@example.com",<br/>  "role": "CUSTOMER"<br/>}'))
story.append(p(
    "The access token is valid for 900 seconds (15 minutes). The shared LoginResponse class also contains "
    "a message field, which is not used by the login flow."
))
story.append(PageBreak())

story.append(p("Errors and Rate Limiting", "H1Custom"))
error_rows = [
    ["Status", "When it is returned", "Response detail"],
    ["400", "Invalid JSON, missing fields, invalid email format", "Request validation failed"],
    ["401", "Unknown email or incorrect password", "Invalid email or password"],
    ["403", "Correct credentials but email is not verified", "Email is not verified"],
    ["429", "Rate limit exceeded", "Too many failed login attempts. Please try again later."],
    ["500", "Unexpected authentication error", "An unexpected authentication error occurred"],
]
story.append(table(error_rows, [16 * mm, 67 * mm, 77 * mm]))
story.append(p("Validation error example", "H2Custom"))
story.append(code('{<br/>  "status": 400,<br/>  "detail": "Request validation failed",<br/>  "errors": {<br/>    "email": "Email must be valid",<br/>    "password": "Password is required"<br/>  }<br/>}'))
story.append(p("Rate-limit behavior", "H2Custom"))
story.append(p(
    "The first five failed password attempts return 401. The next attempt for the same normalized email and direct client IP returns 429. "
    "A successful verified login clears the stored failed-attempt count."
))
story.append(p("In-memory limitation", "H2Custom"))
story.append(p(
    "Counters reset when the backend restarts and are not shared between backend instances. For a multi-instance production deployment, replace this with Redis or gateway-backed rate limiting. "
    "If the application is deployed behind a reverse proxy, configure trusted forwarded-IP handling."
))
story.append(PageBreak())

story.append(p("Security Architecture", "H1Custom"))
components = [
    ["Class", "Responsibility"],
    ["UserRepository", "Retrieves a user with findByEmail"],
    ["JwtProperties", "Reads the Base64 JWT secret and token lifetime"],
    ["JwtService", "Creates and validates HS256 access JWTs"],
    ["JwtAuthenticationFilter", "Reads Authorization: Bearer tokens on protected requests"],
    ["SecurityConfig", "Registers BCrypt, stateless security, public paths, and the JWT filter"],
    ["LoginRateLimitProperties", "Reads failed-attempt count and time-window configuration"],
    ["LoginRateLimitService", "Tracks failures in memory by email and client IP"],
    ["AuthServiceImpl", "Coordinates validation, credentials, verification, rate limiting, and JWT creation"],
    ["AuthExceptionHandler", "Returns consistent ProblemDetail responses for auth failures"],
]
story.append(table(components, [54 * mm, 106 * mm]))
story.append(p("JWT-protected requests", "H2Custom"))
story.append(code("Authorization: Bearer <access-token>"))
story.append(p(
    "Public routes: /api/auth/**, /swagger-ui/**, and /v3/api-docs/**. All other routes require a valid access JWT."
))
story.append(p("Configuration", "H2Custom"))
story.append(code(
    "security.jwt.secret=<base64-secret>\n"
    "security.jwt.access-token-expiration-seconds=900\n\n"
    "security.login-rate-limit.max-failed-attempts=5\n"
    "security.login-rate-limit.window-seconds=900"
))
story.append(PageBreak())

story.append(p("Testing and Swagger", "H1Custom"))
story.append(p("Swagger UI", "H2Custom"))
story.append(code("http://localhost:8080/swagger-ui/index.html"))
story.append(p(
    "Open Authentication, select POST /api/auth/login, choose Try it out, and submit a request. "
    "localhost can be opened only on the same laptop that is running the Spring Boot application."
))
story.append(p("Development test accounts", "H2Custom"))
test_rows = [
    ["Purpose", "Email", "Password", "Expected result"],
    ["Verified login", "test.customer@talabaty.local", "TestPassword123!", "200 with access token"],
    ["Unverified login", "unverified.customer@talabaty.local", "TestPassword123!", "403"],
    ["Wrong password", "test.customer@talabaty.local", "WrongPassword456!", "401"],
]
story.append(table(test_rows, [28 * mm, 56 * mm, 36 * mm, 40 * mm]))
story.append(p("Rate-limit test", "H2Custom"))
story.append(p(
    "Send the wrong-password request five times. Send it once more from the same laptop: the API should return 429. "
    "Restart the application to reset its in-memory counters during development."
))
story.append(p("Production checklist", "H2Custom"))
checklist = [
    "Move the JWT secret and database password from application.properties into environment variables or a secrets manager.",
    "Replace the development JWT secret before deployment because it was exposed during development setup.",
    "Restrict CrossOrigin origins to the real frontend domain.",
    "Add automated tests for validation, credentials, unverified email, rate limiting, and protected routes.",
    "Use Redis or an API gateway for rate limiting when multiple backend instances are deployed.",
]
for item in checklist:
    story.append(p("- " + item))

doc = SimpleDocTemplate(
    OUTPUT, pagesize=A4,
    rightMargin=25 * mm, leftMargin=25 * mm,
    topMargin=20 * mm, bottomMargin=20 * mm,
    title="Talabaty Authentication Module Documentation",
    author="Talabaty Backend Team",
)
doc.build(story, onFirstPage=footer, onLaterPages=footer)
