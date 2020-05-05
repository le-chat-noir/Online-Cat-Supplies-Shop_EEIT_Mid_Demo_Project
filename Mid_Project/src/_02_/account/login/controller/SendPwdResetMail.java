package _02_.account.login.controller;


import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendPwdResetMail {

	// for example, smtp.mailgun.org
	private static final String SMTP_SERVER = "smtp.gmail.com";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";

	private static final String EMAIL_FROM = "";
	private static String EMAIL_TO = "";

	private static final String EMAIL_SUBJECT = "測試用的密碼重設信 (JAVA SMTP)";


	public void sendPwdReset(String mailTo, String textUrl) {

		EMAIL_TO = mailTo;

		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", SMTP_SERVER); // optional, defined in SMTPTransport

		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, null);

		try {
			Message msg = new MimeMessage(session);

			// from
			msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO, false));

			// subject
			msg.setSubject(EMAIL_SUBJECT);

			// content
			StringBuffer text = new StringBuffer();
			text.append("<h2>哈囉!</h2>");
			text.append("<p>這是測試用的密碼重設信。</p>");
			text.append("<p>請點取下方連結重設密碼</p>");
			text.append("<a style='text-decoration: none;' href='http://" + textUrl + "'>http://" + textUrl + "</a></p>");
			msg.setContent(text.toString(), "text/html;charset=UTF-8");

			msg.setSentDate(new Date());

			// Get SMTPTransport
			SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			// connect
			t.connect(SMTP_SERVER, USERNAME, PASSWORD);

			// send
			t.sendMessage(msg, msg.getAllRecipients());

			System.out.println("Response: " + t.getLastServerResponse());

			t.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}