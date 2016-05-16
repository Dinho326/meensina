package br.com.Meensina.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.com.Meensina.entity.Area;
import br.com.Meensina.entity.Categoria;
import br.com.Meensina.entity.Especialidade;

@Stateless
public class ProfessorServiceBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Instância gerenciada pelo Container **/
	@PersistenceContext
	private EntityManager emanager;

	public List<Area> listarAreas() {

		TypedQuery<Area> query = emanager.createNamedQuery(Area.BUSCA_TODAS_AREAS, Area.class);
		List<Area> a = new ArrayList<>();
		a = query.getResultList();
		return a;
	}

	public List<Categoria> listarGrupoDeCategorias(Long areaId) {

		TypedQuery<Categoria> query = emanager.createNamedQuery(Categoria.BUSCA_GRUPO_DE_CATEGORIAS, Categoria.class);
		query.setParameter("area", areaId);
		List<Categoria> categorias = new ArrayList<>();
		categorias = query.getResultList();

		return categorias;
	}

	public List<Especialidade> listarGrupoEspecialidade(Long categoriaId) {

		TypedQuery<Especialidade> query = emanager.createNamedQuery(Especialidade.BUSCA_GRUPO_DE_ESPECIALIDADES,Especialidade.class);
		query.setParameter("categoria", categoriaId);
		List<Especialidade> especialidades = new ArrayList<>();
		especialidades = query.getResultList();

		return especialidades;
	}
}
