package br.com.Meensina.service;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import br.com.Meensina.entity.Usuario;

@Stateless
public class JavaMailApp {

	Usuario usuario = new Usuario();

	public static void emailSimples(Usuario usuario) {

		Properties props = new Properties();
		props =  parametros(props);
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("systemcarestag@hotmail.com", "Estagiarios2015");
			}
		});
		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("systemcarstag@hotmail.com")); // Remetente

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail())); // Destinatário(s)
			message.setSubject("Código de Autenticação para recadastrar senha ");// Assunto
			message.setText("\n É um enorme prazer ter você como um de nossos usuários. \n"
					+ "Segue o código de autenticação para recadastramento de senha :\n\n " + usuario.getAutentica());
			/** Método para enviar a mensagem criada */
			// Transport.send(message);
			System.out.println("Código para verificação " + usuario.getAutentica());
			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Parametros para enviar email
	 **/
	public static Properties parametros(Properties props) {
		/** Parâmetros de conexão com servidor Hotmail */
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.live.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		return props;
	}

	public static void emailDeUsuario(Usuario usuario, String mensagem) {

		Properties props = new Properties();
		props =  parametros(props);
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("systemcarestag@hotmail.com", "Estagiarios2015");
			}
		});
		/** Ativa Debug para sessão */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("systemcarstag@hotmail.com")); // Remetente

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dinho326@gmail.com")); // Destinatário(s)
			message.setSubject("Mensagem do "+ usuario.getCpf());// Assunto
			message.setText(mensagem);
			/** Método para enviar a mensagem criada */
			 Transport.send(message);
			System.out.println("Feito!!!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
