package br.com.Meensina.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="professor")
public class Professor implements Serializable {

	private static final long serialVersionUID = -4628166046112527241L;
	
	
	@Id
	private Long professorId;

	
	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="Usuario_id", nullable=false)
	private Usuario usuario;
	
	
	@Column
	private Date dataNascimento;
	
	@OneToMany(mappedBy="professor")
	private List<ProfessorEspecialidade> professorEspecialidades;
	

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Long getProfessorId() {
		return professorId;
	}


	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
	}


	public List<ProfessorEspecialidade > getProfessorEspecialidades() {
		return professorEspecialidades;
	}


	public void setProfessorEspecialidades(List<ProfessorEspecialidade > professorEspecialidades) {
		this.professorEspecialidades = professorEspecialidades;
	}


}
