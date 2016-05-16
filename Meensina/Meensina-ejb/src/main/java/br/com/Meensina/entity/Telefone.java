package br.com.Meensina.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="telefone")
@NamedQueries({
	@NamedQuery(name = "Telefone.findTelefoneUser", query = "SELECT t FROM Telefone t ")
})
public class Telefone implements Serializable{

	
	private static final long serialVersionUID = 5878055426284545584L;

	public static final String BUSCA_TELEFONE_USUARIO = "Telefone.findTelefoneUser";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telefoneId;
	

	@ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY )
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	
	@Column
	private String telefone;
	
	
	public Long getTelefoneId() {
		return telefoneId;
	}

	public void setTelefoneId(Long telefoneId) {
		this.telefoneId = telefoneId;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


}
