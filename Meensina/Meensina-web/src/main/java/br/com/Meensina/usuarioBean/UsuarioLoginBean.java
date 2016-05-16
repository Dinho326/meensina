package br.com.Meensina.usuarioBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.Meensina.entity.Usuario;
import br.com.Meensina.service.UsuarioServiceBean;
import br.com.Meensina.util.GenericBean;

@ManagedBean(name="usuarioLogin")
@SessionScoped
public class UsuarioLoginBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = 3038540219776563078L;

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	private Usuario usuario;
	private Boolean autenticacao = false;

	@PostConstruct
	public void init() {
		if(usuario == null){
			usuario = new Usuario();
		}
	}

	public String login() {

		usuario.setSenha(usuarioServiceBean.criptografar(usuario.getSenha()));
				
		try {
			usuario = usuarioServiceBean.efetuarLogin(usuario); 
			autenticacao = true;
			return "/paginas/usuario/perfil-configuracao";
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagem("msg.email.senha.incorretos"), ""));
			return null;
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean isAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(Boolean autenticacao) {
		this.autenticacao = autenticacao;
	}

}
