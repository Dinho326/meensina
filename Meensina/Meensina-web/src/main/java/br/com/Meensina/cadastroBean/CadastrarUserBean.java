package br.com.Meensina.cadastroBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.Meensina.entity.Usuario;
import br.com.Meensina.service.UsuarioServiceBean;
import br.com.Meensina.util.GenericBean;
import br.com.Meensina.util.Util;

@ManagedBean
@ViewScoped
public class CadastrarUserBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = -1081666097757424915L;

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	private Usuario usuario;
	private boolean check;
	private String senha;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public String cadastrarUsuario() {

		if (check == false) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagem("msg.aceitar.termos"), ""));

			return null;
		}

		else if (usuarioServiceBean.compararEmail(usuario) == true) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagem("msg.email.existente"), ""));

			return null;

		} else if (usuarioServiceBean.compararCPF(usuario) == true) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagem("msg.cpf.existe"), ""));

			return null;

		} else if (!usuario.getSenha().equals(getSenha())) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, getMensagem("msg.senhas.iguais"), ""));
			return null;
		}
		
		Util.createDir(usuario);
		usuario.setCaminhoFoto("default.png");
		usuario.setSenha(usuarioServiceBean.criptografar(usuario.getSenha()));
		usuarioServiceBean.salvar(usuario);
		return "login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
