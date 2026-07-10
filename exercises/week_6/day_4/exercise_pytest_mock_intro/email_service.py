# email_service.py
import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart


class EmailSendError(Exception):
    """Raised when email sending fails."""
    pass


class EmailService:
    """Service for sending emails via SMTP."""
    
    def __init__(self, host: str, port: int, username: str = None, password: str = None):
        self.host = host
        self.port = port
        self.username = username
        self.password = password
    
    def send(self, to: str, subject: str, body: str) -> dict:
        """Send a single email."""
        try:
            smtp = smtplib.SMTP(self.host, self.port)
            smtp.starttls()
            
            if self.username and self.password:
                smtp.login(self.username, self.password)
            
            message = self.format_message(to, subject, body)
            result = smtp.sendmail(self.username or "noreply@test.com", to, message)
            smtp.quit()
            
            return result
            # return {"success": True, "message_id": "generated_id"}
            
        except smtplib.SMTPAuthenticationError as e:
            raise EmailSendError(f"Authentication failed: {e}")
        except ConnectionError as e:
            raise EmailSendError(f"Connection failed: {e}")
    
    def send_bulk(self, recipients: list, subject: str, body: str) -> list:
        """Send email to multiple recipients."""
        results = []
        for recipient in recipients:
            result = self.send(recipient, subject, body)
            results.append(result)
        return results
    
    def format_message(self, to: str, subject: str, body: str) -> str:
        """Format email message."""
        msg = MIMEMultipart()
        msg['To'] = to
        msg['Subject'] = subject
        msg.attach(MIMEText(body, 'plain'))
        return msg.as_string()