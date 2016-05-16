package br.com.Meensina.usuarioBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import br.com.Meensina.entity.Usuario;
import br.com.Meensina.service.UsuarioServiceBean;
import br.com.Meensina.util.GenericBean;
import br.com.Meensina.util.Util;
import br.com.Meensina.util.WebResources;

@ManagedBean
@ViewScoped
public class UsuarioUpdateBean extends GenericBean{

	private static final long serialVersionUID = 3627225910512824153L;
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	private Usuario usuario;
	private Boolean renderProfessor;
	private UploadedFile file;

	@PostConstruct
	public void init() {

		setUsuario(WebResources.getSessionUsuario());

	}

	public void atualizarDados() throws IOException {

		if (file == null || file.getFileName().equals("")) {

			usuarioServiceBean.update(usuario);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, getMensagem("display.sucesso"), ""));
			//return null;
		}

		else {

			String filename = file.getFileName();
			InputStream is = null;
			OutputStream output = null;
		

			try {

				is = file.getInputstream();
				byte[] bytes = IOUtils.toByteArray(is);
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

				new File(scontext.getRealPath("/resources/fotos")).mkdir();
				File dir = new File(scontext.getRealPath("/resources/fotos/" + usuario.getCpf()));
				if (dir.exists()) {
					Util.removerArquivos(dir);
					dir.delete();
				}
				dir.mkdir();
				

				String nomeArquivo = scontext.getRealPath("/resources/fotos/" + usuario.getCpf() + "/" + filename);
				File arquivo = new File(nomeArquivo);
				output = new FileOutputStream(arquivo);
				output.write(bytes);

				usuario.setCaminhoFoto(file.getFileName());
				usuarioServiceBean.update(usuario);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, getMensagem("display.sucesso"), ""));
				
			} finally {
				if (output != null) {
					output.close();
				}
				if (is != null) {
					is.close();
				}
			}
			
			//return null;
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean isRenderProfessor() {
		return renderProfessor;
	}

	public void setRenderProfessor(Boolean renderProfessor) {
		this.renderProfessor = renderProfessor;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
