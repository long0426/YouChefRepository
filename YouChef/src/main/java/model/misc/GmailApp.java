package model.misc;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service(value="gmailApp")
@Transactional 
public class GmailApp
{
    private static final String SMTP_HOST_NAME = "gmail-smtp.l.google.com";
    private static final String SMTP_AUTH_USER = "eeitgroup3@gmail.com";
    private static final String SMTP_AUTH_PWD = "group123456";
//    private static final String emailMsgTxt = "親愛的 會員您好， 感謝您於優廚申請註冊， 現在您可開始使用會員專屬功能囉！   <br/>"
//    																+ "優廚 敬上   <br/>"
//    																+ "＊此信為系統自動發送，請勿直接回覆，謝謝＊";
//    private static final String emailSubjectTxt = "註冊成功";
//    private static final String emailFromAddress = "YouChef<eeitgroup3@gmail.com>";
//    private static final String[] emailList = { "shuhsien.info@gmail.com", "eeitgroup3@gmail.com"};
 
    public static void main(String args[]) throws Exception
    {
//         System.setProperty( "proxySet", "true" );
//         System.setProperty( "http.proxyHost", "wwwcache.ncl.ac.uk" );
//         System.setProperty( "http.proxyPort", "8080" );
//        
//        GmailApp smtpMailSender = new GmailApp();
//        smtpMailSender.postMail(emailList, emailSubjectTxt, emailMsgTxt, emailFromAddress, name);
//        System.out.println("Sucessfully Sent mail to All Users");
    }
 
    public void postMail(String recipients[], String subject, String message, String from, String name) throws MessagingException
    {
        boolean debug = false;
        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                
        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
 
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);
 
        session.setDebug(debug);
 
        // create a message
        Message msg = new MimeMessage(session);
        
        String encodedSubject = null;
        String encodedBody = null;
        String encodedName = null;
				try {
					encodedSubject = new String(subject.getBytes("UTF-8"),"UTF-8");
					encodedBody = new String(message.getBytes("UTF-8"),"UTF-8");
					encodedName = new String(name.getBytes("UTF-8"), "UTF-8");

					InternetAddress addressFrom = new InternetAddress(from, encodedName);
			        msg.setFrom(addressFrom);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++)
        {
            addressTo[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.TO, addressTo);
 
        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/html; charset=UTF-8");
        Transport.send(msg);
    }
    
    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
}