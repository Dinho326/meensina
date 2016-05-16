package br.com.Meensina.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="area")

@NamedQueries({
	@NamedQuery(name = "Area.findAreas", query = "SELECT a FROM Area a")
})
public class Area implements Serializable {

	private static final long serialVersionUID = 5153557890520577612L;
	public static final String BUSCA_TODAS_AREAS = "Area.findAreas";

	
	
	@Id
	@Column
	private Long areaId;
	
	@Column
	private String descricaoArea;
	
	@OneToMany
	@JoinColumn(name="area_id")
	private List<Categoria > categoria;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getDescricaoArea() {
		return descricaoArea;
	}

	public void setDescricaoArea(String descricaoArea) {
		this.descricaoArea = descricaoArea;
	}

	public  List<Categoria > getCategoria() {
		return categoria;
	}

	public void setCategoria( List<Categoria > categoria) {
		this.categoria = categoria;
	}
	
}
