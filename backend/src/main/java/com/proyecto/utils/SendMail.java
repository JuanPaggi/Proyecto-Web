package com.proyecto.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Enviar mail de verificacion
 */

@Component
public class SendMail {

    @Value("${remitente.mail}")
    private String remitente;

    @Value("${clave.mail}")
    private String claveRemitente;

    public void enviarMail(String mail, String texto) throws Exception {
        try {

            Properties propiedades = System.getProperties();
            propiedades.put("mail.smtp.host", "smtp.gmail.com");
            propiedades.put("mail.smtp.user", remitente);
            propiedades.put("mail.smtp.clave", claveRemitente);
            propiedades.put("mail.smtp.auth", "true");
            propiedades.put("mail.smtp.starttls.enable", "true");
            propiedades.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(propiedades);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            message.setSubject("Correo de verificacion");
            message.setText(texto);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, claveRemitente);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (Exception error) {
            throw error;
        }
    }

}
