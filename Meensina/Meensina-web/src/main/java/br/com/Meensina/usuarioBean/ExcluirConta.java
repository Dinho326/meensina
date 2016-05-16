package br.com.Meensina.usuarioBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.Meensina.entity.Usuario;
import br.com.Meensina.service.UsuarioServiceBean;
import br.com.Meensina.util.WebResources;

@ManagedBean
@ViewScoped
public class ExcluirConta {

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	private Usuario usuario;
	
	
	@PostConstruct
	public void init() {

		setUsuario(WebResources.getSessionUsuario());

	}
	
	public String direcionaParaCadastro(){
		
		return "/paginas/principal/cadastrar";
	}
	
	public String desfazerConta(){
		
		usuarioServiceBean.remove(usuario);
		return "encerrar-conta";
	}


	@PreDestroy
	public void finalize(){
		usuario = null; 
	}
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
