package com.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtilGmail
{
  public MailUtilGmail() {}
  
  //Method that set mail properties and send Mail to the user
  
  public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML)
    throws MessagingException
  {
    Properties props = new Properties();
    props.put("mail.transport.protocol", "smtps");
    props.put("mail.smtps.host", "smtp.gmail.com");
    props.put("mail.smtps.port", 465);
    props.put("mail.smtps.auth", "true");
    props.put("mail.smtps.quitwait", "false");
    Session session = Session.getDefaultInstance(props);
    session.setDebug(true);
    

    Message message = new MimeMessage(session);
    message.setSubject(subject);
    if (bodyIsHTML) {
      message.setContent(body, "text/html");
    } else {
      message.setText(body);
    }
    

    Address fromAddress = new InternetAddress(from);
    Address toAddress = new InternetAddress(to);
    message.setFrom(fromAddress);
    message.setRecipient(Message.RecipientType.TO, toAddress);
    

    Transport transport = session.getTransport();
    
    transport.connect("jkaminenreddy@gmail.com", "jkaminenreddy1221");
    transport.sendMessage(message, message.getAllRecipients());
    transport.close();
  }
}


