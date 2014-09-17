package utilitaire;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
    
public static void sendMail(String adrMail,String subjet, String texte) throws AddressException, MessagingException {

    
    
    
    
    
    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session s = Session.getInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("carpediemafpa0602@gmail.com", "Lol9901!");
            }
        });

            Message message = new MimeMessage(s);
            InternetAddress recipient = new InternetAddress(adrMail);
            message.setRecipient(Message.RecipientType.TO, recipient);
            message.setSubject(subjet);
            message.setText(texte);

            Transport.send(message);
    }       
    
}
