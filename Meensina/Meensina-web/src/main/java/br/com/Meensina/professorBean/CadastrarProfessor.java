package br.com.Meensina.professorBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.Meensina.entity.Area;
import br.com.Meensina.entity.Categoria;
import br.com.Meensina.entity.Especialidade;
import br.com.Meensina.entity.Usuario;
import br.com.Meensina.service.ProfessorServiceBean;
import br.com.Meensina.util.WebResources;

@ManagedBean
@ViewScoped
public class CadastrarProfessor implements Serializable{

	private static final long serialVersionUID = 1L;
	@EJB
	private ProfessorServiceBean professorServiceBean;
	private Usuario usuario;
	private List<Area> areas;
	private List<Categoria> categorias;
	private List<Especialidade> especialidades;
	private Long areaSelecionada;
	private Long categoriaSelecionada;
	private Long especialidadeSelecionada;
	
	@PostConstruct
	public void init(){
		
		setUsuario(WebResources.getSessionUsuario());
		areas = professorServiceBean.listarAreas();
		this.setCategoriaSelecionada((long) 0);
		this.setEspecialidadeSelecionada((long) 0);
	}
	
	
	public void formularioDois(){
		
		
	}
	
	public void listarGrupoCategorias(){
		
		this.setCategoriaSelecionada((long) 0);
		this.setEspecialidadeSelecionada((long) 0);
		categorias = professorServiceBean.listarGrupoDeCategorias(areaSelecionada);
	}

	public void listarGrupoEspecialidade(){
		
		this.setEspecialidadeSelecionada((long) 0);
		especialidades = professorServiceBean.listarGrupoEspecialidade(categoriaSelecionada);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public List<Area> getAreas() {
		return areas;
	}



	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}



	public List<Categoria> getCategorias() {
		return categorias;
	}



	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}



	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}



	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}



	public Long getAreaSelecionada() {
		return areaSelecionada;
	}



	public void setAreaSelecionada(Long areaSelecionada) {
		this.areaSelecionada = areaSelecionada;
	}

	public Long getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(Long categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public Long getEspecialidadeSelecionada() {
		return especialidadeSelecionada;
	}

	public void setEspecialidadeSelecionada(Long especialidadeSelecionada) {
		this.especialidadeSelecionada = especialidadeSelecionada;
	}
}
