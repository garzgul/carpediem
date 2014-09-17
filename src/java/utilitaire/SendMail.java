package utilitaire;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import static jdk.nashorn.internal.objects.NativeRegExp.source;

public class SendMail {

    public SendMail() {

    }

    public static boolean sendMail(String adrMail, String subject, String texte, HttpServletRequest request) throws AddressException, MessagingException {

        String result;
        Date sendDate = new Date();
        //Date receivedDate = null;
        String CHARSET = "charset=UTF-8";
        //from, expediteur
//        adrMail = request.getParameter("votremail");
//        // subject, sujet du mail
//        subject = request.getParameter("objetcontact");
//        String votrenom = request.getParameter("votrenom");
//        String votreprenom = request.getParameter("votreprenom");
//        texte = request.getParameter("messagecontact");
        //  email de retour, destinataire
        // String to = "emmanuelle_esnaud@hotmail.com";
        // envoi de l'email depuis localhost
        // String host = "";//hotmail host

        // propriétés systeme
        //Properties properties = System.getProperties();
        Properties props = new Properties();

        //initialisation mail server
//        properties.setProperty("mail.smtp.host", host);//smtp hotmail +port
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // mise en session
        //Session mailSession = Session.getDefaultInstance(properties);
        Session s = Session.getInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("carpediemafpa0602@gmail.com", "Lol9901!");
            }
        });

        try {

            // MimeMessage par default
//          MimeMessage message = new MimeMessage(mailSession);
            Message message = new MimeMessage(s);

            // From: header field of the header.
//          message.setFrom(new InternetAddress(votremail));
            InternetAddress recipient = new InternetAddress(adrMail);

            // etablir les entetes des destinataires: header field of the header.
//             message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//             message.setRecipients(Message.RecipientType.TO, mailMsg.getTo());
//            message.setRecipients(Message.RecipientType.CC, mailMsg.getCc());
//            message.setRecipients(Message.RecipientType.BCC, mailMsg.getBcc());
            message.setRecipient(Message.RecipientType.TO, recipient);

            // donner le sujet : header field
            //message.setSubject("Sujet de votre mail");
            //message.setSubject(mailMsg.getSubject());
            message.setSubject(subject);

            // remplir le message
//          messageBodyPart.setText("Votre message");
            message.setText(texte);

//                
//                
//                
//                
            //      // Create the message part 
            //      BodyPart messageBodyPart = new MimeBodyPart();
//                
            // Contenu + pièces jointes + images
//           MimeMultipart related = new MimeMultipart("related");
//            MimeMultipart attachment = new MimeMultipart("mixed");
//            MimeMultipart alternative = new MimeMultipart("alternative");
//            String[] attachments = mailMsg.getAttachmentURL();
//            String body = (String) mailMsg.getContent();
//            boolean html = mailMsg.isHtml();
//            if (null != attachments) {
//                setAttachmentPart(attachments, related, attachment, body, html);
//            }
//            if (html && null != body) {
//                setHtmlText(related, alternative, body);
//            }
//            setContent(message, alternative, attachment, body);
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
            // Date d'envoi
            //message.setSentDate();
            // envoi messsage
            Transport.send(message);
            String title = "Envoi d'email";
            result = "Message bien envoyé!";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            result = "Une erreur est survenue lors de l'envoi du message!";
        }

        return true;
    }

    public static boolean sendMailPJ(String adrMailpj, String subjectpj, String fichierpj, String textepj, HttpServletRequest request) throws AddressException, MessagingException {

        String result;
        Date sendDate = new Date();
        //Date receivedDate = null;
        String CHARSET = "charset=UTF-8";

        // propriétés systeme
        //Properties properties = System.getProperties();
        Properties props = new Properties();

        //initialisation mail server
//        properties.setProperty("mail.smtp.host", host);//smtp hotmail +port
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // mise en session
        //Session mailSession = Session.getDefaultInstance(properties);
        Session s = Session.getInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("carpediemafpa0602@gmail.com", "Lol9901!");
            }
        });

        try {

            // MimeMessage par default
//          MimeMessage message = new MimeMessage(mailSession);
            Message message = new MimeMessage(s);

            // From: header field of the header.
//          message.setFrom(new InternetAddress(votremail));
            InternetAddress recipient = new InternetAddress(adrMailpj);

            // etablir les entetes des destinataires: header field of the header.
//             message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//             message.setRecipients(Message.RecipientType.TO, mailMsg.getTo());
//            message.setRecipients(Message.RecipientType.CC, mailMsg.getCc());
//            message.setRecipients(Message.RecipientType.BCC, mailMsg.getBcc());
            message.setRecipient(Message.RecipientType.TO, recipient);

            // donner le sujet : header field
            //message.setSubject("Sujet de votre mail");
            //message.setSubject(mailMsg.getSubject());
            message.setSubject(subjectpj);

            // remplir le message
//          messageBodyPart.setText("Votre message");
            message.setText(textepj);

             // Création du fichier à inclure
            // Create the message part 
            BodyPart messageBodyPart = new MimeBodyPart();
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();

            String filename = fichierpj;  //"file.txt"; //source.getName();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            message.setContent(multipart);
            // Date d'envoi
            //message.setSentDate();
            // envoi messsage
            Transport.send(message);
            //String title = "Envoi d'email";
            result = "Message et pièce jointe bien envoyés!";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            result = "Une erreur est survenue lors de l'envoi du message et de sa pièce jointe!";
        }

        return true;
    }

     public static boolean sendMailConfirmCde(String adrMailcde, String subjectcde, String textecde, HttpServletRequest request) throws AddressException, MessagingException {

        String result;
        Date sendDate = new Date();
        //Date receivedDate = null;
        String CHARSET = "charset=UTF-8";
        //from, expediteur
//        adrMail = request.getParameter("votremail");
//        // subject, sujet du mail
//        subject = request.getParameter("objetcontact");
//        String votrenom = request.getParameter("votrenom");
//        String votreprenom = request.getParameter("votreprenom");
//        texte = request.getParameter("messagecontact");
        //  email de retour, destinataire
        // String to = "emmanuelle_esnaud@hotmail.com";
        // envoi de l'email depuis localhost
        // String host = "";//hotmail host

        // propriétés systeme
        //Properties properties = System.getProperties();
        Properties props = new Properties();

        //initialisation mail server
//        properties.setProperty("mail.smtp.host", host);//smtp hotmail +port
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // mise en session
        //Session mailSession = Session.getDefaultInstance(properties);
        Session s = Session.getInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("carpediemafpa0602@gmail.com", "Lol9901!");
            }
        });

        try {

            // MimeMessage par default
//          MimeMessage message = new MimeMessage(mailSession);
            Message message = new MimeMessage(s);

            // From: header field of the header.
//          message.setFrom(new InternetAddress(votremail));
            InternetAddress recipient = new InternetAddress(adrMailcde);

            // etablir les entetes des destinataires: header field of the header.
//             message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//             message.setRecipients(Message.RecipientType.TO, mailMsg.getTo());
//            message.setRecipients(Message.RecipientType.CC, mailMsg.getCc());
//            message.setRecipients(Message.RecipientType.BCC, mailMsg.getBcc());
            message.setRecipient(Message.RecipientType.TO, recipient);

            // donner le sujet : header field
            //message.setSubject("Sujet de votre mail");
            //message.setSubject(mailMsg.getSubject());
            message.setSubject(subjectcde);

            // remplir le message
//          messageBodyPart.setText("Votre message");
            message.setText(textecde);

//                
//                
//                
//                
            //      // Create the message part 
            //      BodyPart messageBodyPart = new MimeBodyPart();
//                
            // Contenu + pièces jointes + images
//           MimeMultipart related = new MimeMultipart("related");
//            MimeMultipart attachment = new MimeMultipart("mixed");
//            MimeMultipart alternative = new MimeMultipart("alternative");
//            String[] attachments = mailMsg.getAttachmentURL();
//            String body = (String) mailMsg.getContent();
//            boolean html = mailMsg.isHtml();
//            if (null != attachments) {
//                setAttachmentPart(attachments, related, attachment, body, html);
//            }
//            if (html && null != body) {
//                setHtmlText(related, alternative, body);
//            }
//            setContent(message, alternative, attachment, body);
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
            // Date d'envoi
            //message.setSentDate();
            // envoi messsage
            Transport.send(message);
            String title = "Envoi d'email";
            result = "Message bien envoyé!";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            result = "Une erreur est survenue lors de l'envoi du message!";
        }

        return true;
    }


    // Convertit un texte au format html en texte brut
    public static final String HtmlenTexte(String stringhtmltexte) {
        HTMLEditorKit kit = new HTMLEditorKit();
        Document doc = kit.createDefaultDocument();
        try {
            kit.read(new StringReader(stringhtmltexte), doc, 0);
            return doc.getText(0, doc.getLength()).trim();
        } catch (IOException ioe) {
            return stringhtmltexte;
        } catch (BadLocationException ble) {
            return stringhtmltexte;
        }
    }

}
