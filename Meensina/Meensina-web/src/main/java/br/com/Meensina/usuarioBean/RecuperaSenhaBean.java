package br.com.Meensina.usuarioBean;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import br.com.Meensina.entity.Usuario;
import br.com.Meensina.service.JavaMailApp;
import br.com.Meensina.service.UsuarioServiceBean;
import br.com.Meensina.util.GenericBean;

@ManagedBean(name = "recuperaSenha")
@ViewScoped
public class RecuperaSenhaBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = 4310781844406252259L;

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	private Usuario usuario;
	private String repeteSenha;
	private boolean render = false;
	private boolean novaSenha = false;
	private boolean enviar = true;
	private String codigoUser;

	@PostConstruct
	public void init() {
		if (usuario == null) {
			usuario = new Usuario();
		}
	}

	public String verificaUsuario() throws EmailException {

		if (!usuarioServiceBean.compararEmail(usuario) == true) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagem("msg.email.nao.existe.bd"), ""));
			
			return null;
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, getMensagem("msg.recebera.um.email"),""));
		setRender(true);
		setEnviar(false);
		geraCodigo();
		JavaMailApp.emailSimples(usuario);
		
		return null;
	}

	public String confirmaCodigo() {

		if (usuario.getAutentica().equals(getCodigoUser())) {
			setRender(false);
			setNovaSenha(true);
			System.out.println("Codigo correto");

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, getMensagem("msg.codigo.correto"), ""));
			
			return null;
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagem("msg.codigo.incorreto"), ""));
		
		return null;
	}

	public String salvarSenha() {

		try {

			if (usuario.getSenha().equals(repeteSenha)) {

				usuario = usuarioServiceBean.buscaEmail(usuario.getEmail());
				
				usuario.setSenha(usuarioServiceBean.criptografar(repeteSenha));
				
				usuarioServiceBean.update(usuario);
				
				return "/paginas/usuario/perfil-configuracao";
				
			}else{
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,getMensagem("msg.senhas.iguais"), ""));
			}
			
			

			

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagem("msg.email.nao.existe.bd"), ""));
		}
		
		return null;

	}

	public void geraCodigo() {
		Random codigo = new Random();
		String codigoUsuario;
		codigoUsuario = usuario.getEmail().substring(0, 3).toUpperCase() + "-";
		for (int i = 0; i < 6; i++) {
			codigoUsuario += codigo.nextInt(9);
		}
		
		usuario.setAutentica(codigoUsuario);
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public String getCodigoUser() {
		return codigoUser;
	}

	public void setCodigoUser(String codigoUser) {
		this.codigoUser = codigoUser;
	}

	public boolean isNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(boolean novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getRepeteSenha() {
		return repeteSenha;
	}

	public void setRepeteSenha(String repeteSenha) {
		this.repeteSenha = repeteSenha;
	}

	public boolean isEnviar() {
		return enviar;
	}

	public void setEnviar(boolean enviar) {
		this.enviar = enviar;
	}

}
