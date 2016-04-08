package com.project.traffic.mail;

import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.project.traffic.constants.MailConstants;
import com.project.traffic.model.BookTicket;
import com.project.traffic.model.CoPassenger;
import com.project.traffic.model.User;
import com.project.traffic.security.SecurityUtil;
import com.project.traffic.util.FileUtil;

public class SSLMailUtil {
	
	private static final Logger LOGGER = Logger.getLogger(SSLMailUtil.class); 

	
	
	static boolean sendMail(String to,String subject,String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		final SecurityUtil sec =new SecurityUtil();
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				try {
					return new PasswordAuthentication(
							sec.decryption(FileUtil.getvalue("adminMail")),
							sec.decryption(FileUtil.getvalue("password")));
				} catch (Exception e) {
					LOGGER.info("Mail auth failed", e);
					System.err.println("Failed to send mail.Please call admin.");
				}
				return null;
			}
		});

		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sec.decryption(FileUtil.getvalue("adminMail"))));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject(subject);
			
			Multipart mp = new MimeMultipart();

	        MimeBodyPart mime = new MimeBodyPart();
	        mime.setContent(body, "text/html");
	        mp.addBodyPart(mime);
			msg.setContent(mp);

			Transport.send(msg);
			return true;
		} catch (Exception e) {
			LOGGER.info("Mail sending failed", e);
			System.err.println("Failed to send mail.Please call admin.");
		}
		return false;
	}
	public static boolean sendWelcomeMail(User user) {
		try {
			String body = FileUtil.readFile("bodyContent.html");
			body = body.replaceAll("<tomail>", (user.getEmail() == null)?"":user.getEmail());
			body = body.replaceAll("<company>", MailConstants.COMPANY);
			body = body.replaceAll("<welcome>", MailConstants.WELCOME_AIRWAYS);
			body = body.replaceAll("<password>", (user.getPassword() == null)?"":user.getPassword());
			
			sendMail((user.getEmail() == null)?"":user.getEmail(), MailConstants.WELCOME_AIRWAYS, body);
			return true;
		} catch (Exception e) {
			LOGGER.info("Failed to send forgot password mail",e);
		}
		return false;
	}
	
	public static boolean sendForgotPasswordMail(User user) {
		try {
			String body = FileUtil.readFile("passwordRecovery.html");
			body = body.replaceAll("<tomail>", (user.getEmail() == null)?"":user.getEmail());
			body = body.replaceAll("<password>", (user.getPassword() == null)?"":user.getPassword());
			
			sendMail((user.getEmail() == null)?"":user.getEmail(), MailConstants.FORGOT_PASSWORD, body);
			return true;
		} catch (Exception e) {
			LOGGER.info("Failed to send forgot password mail",e);
		}
		return true;
	}
	
	public static boolean sendTicketBookingMail(BookTicket ticket,Set<CoPassenger> coPassengers,String status) {
		try {
			String body = FileUtil.readFile("success_book.html");
			body = body.replaceAll("<bookid>", ticket.getId()+"");
			body = body.replaceAll("<bookdate>", ticket.getBookedDate());
			body = body.replaceAll("<source>", ticket.getTransportaionDetails().getSource());
			body = body.replaceAll("<destination>", ticket.getTransportaionDetails().getDestination());
			body = body.replaceAll("<traveldate>", ticket.getTravelDate());
			body = body.replaceAll("<arrival>", ticket.getTransportaionDetails().getArrival());
			body = body.replaceAll("<departure>", ticket.getTransportaionDetails().getDeparture());
			body = body.replaceAll("<company>", MailConstants.COMPANY);
			body = body.replaceAll("<duration>", ticket.getTransportaionDetails().getJourneyTime());
			body = body.replaceAll("<airline>", ticket.getTransportaionDetails().getProvider());
			body = body.replaceAll("<age>", ticket.getUser().getAge()+"");
			body = body.replace("<ticketid>", ticket.getId()+ticket.getTransportaionDetails().getSource().substring(1, 3));
			body = body.replaceAll("<passenger>", ticket.getUser().getFirstName()+" "+ticket.getUser().getLastName());
			
			String temp = "";
			for (CoPassenger coPassenger : coPassengers) {
				String copass = FileUtil.readFile("copassanger.html");
				copass = copass.replaceAll("<airline>", ticket.getTransportaionDetails().getProvider());
				copass = copass.replaceAll("<age>", coPassenger.getAge()+"");
				copass = copass.replace("<ticketid>", coPassenger.getId()+ticket.getTransportaionDetails().getSource().substring(1, 3));
				copass = copass.replaceAll("<passenger>", coPassenger.getName());
				temp += copass;
			}
			
			body = body.replace("<copassengers>", temp);
			sendMail(ticket.getUser().getEmail() ,status, body);
			return true;
		} catch (Exception e) {
			LOGGER.info("Failed to send forgot password mail",e);
		}
		return true;
	}
}