package br.com.Meensina.service;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.Meensina.entity.Usuario;

@Stateless
public class UsuarioServiceBean implements Serializable {

	private static final long serialVersionUID = 4101789868238727341L;

	/** Instância gerenciada pelo Container **/
	@PersistenceContext
	private EntityManager emanager;

	/**
	 * Método para salvar usuário no Banco de dados
	 * 
	 * @param Usuario
	 **/
	public void salvar(Usuario u) {
		u.setPerfil(1);
		emanager.persist(u);
	}

	public void update(Usuario u){
		
		emanager.merge(u);
	}
	
	public void remove(Usuario u){
		
		emanager.remove(u);
	}
	
	public Usuario buscaEmail(String email) throws Exception{
		
		
			TypedQuery<Usuario> query = emanager.createNamedQuery(Usuario.BUSCA_EMAIL, Usuario.class);
			query.setParameter("email", email);
			Usuario u = new Usuario();
			u = query.getSingleResult();

			return u;
	}
	
	
	

	/**
	 * Método que verifica se o email de usuário já está cadastrado no banco de
	 * dados
	 * 
	 * @param Usuario
	 **/
	public Boolean compararEmail(Usuario u) {

		TypedQuery<Usuario> query = emanager.createNamedQuery(Usuario.BUSCA_EMAIL, Usuario.class);
		query.setParameter("email", u.getEmail());
		if (query.getResultList().isEmpty()) {

			return false;
		} else {
			return true;
		}
	}

	/**
	 * Método para verificar autenticidade de login
	 * 
	 * @param usuario
	 **/

	public Usuario efetuarLogin(Usuario u) throws Exception{

		TypedQuery<Usuario> query = emanager.createNamedQuery(Usuario.BUSCA_EMAIL_SENHA, Usuario.class);
		query.setParameter("email", u.getEmail());
		query.setParameter("senha", u.getSenha());

		Usuario user = new Usuario();

		try {
			user = query.getSingleResult();

			return user;

		} catch (NoResultException e) {
			throw new Exception(e);
		}

	}

	/**
	 * Método que verifica se o cpf de usuário já está cadastrado no banco de
	 * dados
	 * 
	 * @param Usuario
	 **/
	public Boolean compararCPF(Usuario u) {

		TypedQuery<Usuario> query = emanager.createNamedQuery(Usuario.BUSCA_CPF, Usuario.class);
		query.setParameter("cpf", u.getCpf());
		if (query.getResultList().isEmpty()) {

			return false;
		} else {
			return true;
		}
	}

	/***
	 * 
	 * Encripta Senha
	 * 
	 ***/
	private static MessageDigest md = null;
	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	private static char[] hexCodes(byte[] text) {
		char[] hexOutput = new char[text.length * 2];
		String hexString;
		for (int i = 0; i < text.length; i++) {
			hexString = "00" + Integer.toHexString(text[i]);
			hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
		}
		return hexOutput;
	}

	public String criptografar(String senha) {
		if (md != null) {
			return new String(hexCodes(md.digest(senha.getBytes())));
		}
		return null;
	}
}
