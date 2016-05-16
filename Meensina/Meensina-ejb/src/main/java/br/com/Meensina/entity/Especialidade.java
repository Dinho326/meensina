package br.com.Meensina.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="especialidade")
@NamedQueries({
	@NamedQuery(name = "Especialidade.findGrupoEspecialidades", query = "SELECT e FROM Especialidade e WHERE e.categoria.categoriaId = :categoria")
})
public class Especialidade implements Serializable {

	private static final long serialVersionUID = 7504077273497407783L;
	public static final String BUSCA_GRUPO_DE_ESPECIALIDADES = "Especialidade.findGrupoEspecialidades";

	@Id
	private Long especialidadeId;
	
	@Column
	private String descricaoEspecialidade;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	
	@OneToMany(mappedBy="especialidade")
	private List<ProfessorEspecialidade > professorEspecialidades;
	
	public Long getEspecialidadeId() {
		return especialidadeId;
	}

	public void setEspecialidadeId(Long especialidadeId) {
		this.especialidadeId = especialidadeId;
	}

	public String getDescricaoEspecialidade() {
		return descricaoEspecialidade;
	}

	public void setDescricaoEspecialidade(String descricaoEspecialidade) {
		this.descricaoEspecialidade = descricaoEspecialidade;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<ProfessorEspecialidade > getProfessorEspecialidades() {
		return professorEspecialidades;
	}

	public void setProfessorEspecialidades(List<ProfessorEspecialidade > professorEspecialidades) {
		this.professorEspecialidades = professorEspecialidades;
	}

}
