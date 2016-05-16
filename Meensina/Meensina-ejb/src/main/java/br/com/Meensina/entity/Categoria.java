package br.com.Meensina.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
@NamedQueries({
	@NamedQuery(name = "Categoria.findGrupoCategorias", query = "SELECT c FROM Categoria c WHERE c.area.areaId = :area")
})
public class Categoria implements Serializable {

	private static final long serialVersionUID = -8506244983757507739L;
	public static final String BUSCA_GRUPO_DE_CATEGORIAS = "Categoria.findGrupoCategorias";

	@Id
	@Column
	private Long categoriaId;
	
	@Column
	private String descricaoCategoria;
	
	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="area_id")
	private Area area;
	
	
	@OneToMany
	@JoinColumn(name="categoria_id")
	private List<Especialidade > especialidade;
	

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public List<Especialidade > getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(List<Especialidade > especialidade) {
		this.especialidade = especialidade;
	}
	
	
}
