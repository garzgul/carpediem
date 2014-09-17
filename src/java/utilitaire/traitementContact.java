package utilitaire;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author cdi108
 */
public class traitementContact {
    
//    public boolean envoimailcontact()
//    {
//        String result;
//            //from, expediteur
//            String votremail = request.getParameter("votremail");
//            // subject, sujet du mail
//            String objetcontact = request.getParameter("objetcontact");
//            String votrenom = request.getParameter("votrenom");
//            String votreprenom = request.getParameter("votreprenom");
//            String messagecontact = request.getParameter("messagecontact");
//            //  email de retour, destinataire
//            String to = "emmanuelle_esnaud@hotmail.com";
//            // envoi de l'email depuis localhost
//            String host = "";//hotmail host
//
//            // proprietes du systeme
//            Properties properties = System.getProperties();
//            // initialisation mail server
//            properties.setProperty("mail.smtp.host", host);//smtp hotmail +port
//            // Mise en session
//            Session mailSession = Session.getDefaultInstance(properties);
//            try {
//                // MimeMessage par default
//                MimeMessage message = new MimeMessage(mailSession);
//                // Set From: header field of the header.
//                message.setFrom(new InternetAddress(votremail));
//                // Set To: header field of the header.
//                message.addRecipient(Message.RecipientType.TO,
//                        new InternetAddress(to));
//                // Set Subject: header field
//                message.setSubject("Sujet de votre mail");
//                // Create the message part 
//                BodyPart messageBodyPart = new MimeBodyPart();
//                // Fill the message
//                messageBodyPart.setText("Votre message");
//                // Create a multipar message
//                Multipart multipart = new MimeMultipart();
//                // Set text message part
//                multipart.addBodyPart(messageBodyPart);
//                // Part two is attachment
//                messageBodyPart = new MimeBodyPart();
//                String filename = "file.txt";
//                DataSource source = new FileDataSource(filename);
//                messageBodyPart.setDataHandler(new DataHandler(source));
//                messageBodyPart.setFileName(filename);
//                multipart.addBodyPart(messageBodyPart);
//                // Send the complete message parts
//                message.setContent(multipart);
//                // Send message
//                Transport.send(message);
//                String title = "Envoi d'email";
//                result = "Message bien envoyé!";
//            } catch (MessagingException mex) {
//                mex.printStackTrace();
//                result = "Une erreur est survenue lors de l'envoi du message!";
//            }
//    }
//    
    
    
    
    
}
