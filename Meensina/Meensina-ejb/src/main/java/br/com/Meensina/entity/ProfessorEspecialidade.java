package br.com.Meensina.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="professor_especialidade")
public class ProfessorEspecialidade implements Serializable{

	private static final long serialVersionUID = -8554592367053322763L;

	@EmbeddedId
	private ProfessorEspecialidadePK id;

	@MapsId("professorId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="professor_id")
	private Professor professor;

	@MapsId("especialidadeId")
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="especialidade_id")
	private Especialidade especialidade;
	
	@Column
	private double valorLocal;
	
	@Column
	private double valorOnline;
	
	@Column
	private String formacao;
	
	
	
	
	
	public double getValorLocal() {
		return valorLocal;
	}
	public void setValorLocal(double valorLocal) {
		this.valorLocal = valorLocal;
	}
	public double getValorOnline() {
		return valorOnline;
	}
	public void setValorOnline(double valorOnline) {
		this.valorOnline = valorOnline;
	}
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public ProfessorEspecialidadePK getId() {
		return id;
	}
	public void setId(ProfessorEspecialidadePK id) {
		this.id = id;
	}
	
}
