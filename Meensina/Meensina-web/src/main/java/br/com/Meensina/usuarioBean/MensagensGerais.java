package br.com.Meensina.usuarioBean;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.Meensina.entity.Usuario;
import br.com.Meensina.service.JavaMailApp;
import br.com.Meensina.util.GenericBean;
import br.com.Meensina.util.WebResources;

@ManagedBean
@RequestScoped
public class MensagensGerais extends GenericBean {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private String mensagem;
	
	@PostConstruct
	public void init(){
		setUsuario(WebResources.getSessionUsuario());
	}
	
	public void enviarMensagem(){
		
		JavaMailApp.emailDeUsuario(usuario, mensagem);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, getMensagem("msg.sucesso"), ""));
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
