package com.xwintop.xtest.email_sdk;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

// Referenced classes of package com.kingtrust.util.email:
//            MailSenderInfo, MyAuthenticator

public class SimpleMailSender {

	public SimpleMailSender() {
	}

	public boolean sendTextMail(MailSenderInfo mailInfo) {
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate())
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			javax.mail.Address from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			javax.mail.Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(javax.mail.Message.RecipientType.TO, to);
			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			Transport.send(mailMessage);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate())
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			javax.mail.Address from = new InternetAddress(mailInfo.getFromAddress());
			mailMessage.setFrom(from);
			javax.mail.Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(javax.mail.Message.RecipientType.TO, to);
			mailMessage.setSubject(mailInfo.getSubject());
			mailMessage.setSentDate(new Date());
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			mailMessage.setContent(mainPart);
			Transport.send(mailMessage);
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}
