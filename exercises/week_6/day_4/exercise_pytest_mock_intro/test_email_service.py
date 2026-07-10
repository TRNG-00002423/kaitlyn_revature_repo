import pytest
from email_service import EmailService, EmailSendError

class TestEmailService:
    """Tests for EmailService using pytest-mock."""
    def test_send_email_creates_smtp_connection(self, mocker):
        """Verify SMTP connection is established."""
        # create a mock SMTP client
        mock_smtp = mocker.Mock()

        # patch the SMTP class
        mocker.patch('email_service.smtplib.SMTP', return_value=mock_smtp)

        # create service and send email
        service = EmailService(host="smtp.test.com", port=587)
        service.send("to@test.com", "subject", "body")

        # verify that the SMTP was established with the correct args
        assert mock_smtp.starttls.called
        # assert mock_smtp.login.called #??

def test_send_email_returns_message_id(mocker):
    """Verify send returns message ID from SMTP."""
    mock_smtp = mocker.Mock()
    mock_smtp.sendmail.return_value = {"message_id": "12345"}

    mocker.patch('email_service.smtplib.SMTP', return_value=mock_smtp)

    service = EmailService(host="smtp.test.com", port=587)
    result = service.send("to@test.com", "subject", "body")

    assert result["message_id"] == "12345"

def test_send_email_passes_correct_arguments(mocker):
    """Verify correct data sent to SMTP."""
    mock_smtp = mocker.Mock()
    mocker.patch('email_service.smtplib.SMTP', return_value=mock_smtp)

    service = EmailService(host="smtp.test.com", port=587)
    service.send("to@test.com", "Test Subject", "Test Body")

    # Verify that sendmail was called with correct args
    mock_smtp.sendmail.assert_called_once()
    call_args = mock_smtp.sendmail.call_args

    assert "to@test.com" in str(call_args)
    assert "Test Subject" in str(call_args)

def test_send_email_handles_connection_error(mocker):
    """Verify graceful handling of connection failure."""
    mock_smtp = mocker.Mock()
    mock_smtp.starttls.side_effect = ConnectionError("Failed to connect") # previously mock_smtp.start (a non-existent method?)

    mocker.patch('email_service.smtplib.SMTP', return_value=mock_smtp)

    service = EmailService(host="smtp.test.com", port=587)

    with pytest.raises(EmailSendError) as exc_info:
        service.send("to@test.com", "Subject", "Body")

    assert "connection" in str(exc_info.value).lower()


def test_send_email_handles_authentication_error(mocker):
    """Verify handling of auth failure."""
    import smtplib
    
    mock_smtp = mocker.Mock()
    mock_smtp.starttls.side_effect = smtplib.SMTPAuthenticationError(535, b"Auth failed")
    
    mocker.patch('email_service.smtplib.SMTP', return_value=mock_smtp)
    
    service = EmailService(host="smtp.test.com", port=587)
    
    with pytest.raises(EmailSendError):
        service.send("to@test.com", "Subject", "Body")

def test_send_bulk_emails_sends_to_all_recipients(mocker):
    """Verify bulk send reaches all recipients."""
    mock_smtp = mocker.Mock()
    mocker.patch('email_service.smtplib.SMTP', return_value=mock_smtp)

    service = EmailService(host="smtp.test.com", port=587)
    recipients = ["a@test.com", "b@test.com", "c@test.com"]
    service.send_bulk(recipients, "Subject", "Body")

    assert mock_smtp.sendmail.call_count == 3

def test_send_email_quits_connection(mocker):
    """Verify SMTP connection is properly closed."""
    mock_smtp = mocker.Mock()
    mocker.patch('email_service.smtplib.SMTP', return_value=mock_smtp)

    service = EmailService(host="smtp.test.com", port=587)
    service.send("to@test.com", "Subject", "Body")

    # verify quit was called
    mock_smtp.quit.assert_called_once()

def test_email_formatter_called(mocker):
    """Verify email formatting is applied."""
    mock_smtp = mocker.Mock()
    mocker.patch('email_service.smtplib.SMTP', return_value=mock_smtp)
    
    service = EmailService(host="smtp.test.com", port=587)

    # spy on the format_message method
    spy = mocker.spy(service, 'format_message')

    service.send("to@test.com", "Subject", "Body")

    # verify that format_message was called
    spy.assert_called_once_with("to@test.com", "Subject", "Body")