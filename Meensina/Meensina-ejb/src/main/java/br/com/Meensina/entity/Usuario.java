package br.com.Meensina.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="usuario")
@NamedQueries({
	@NamedQuery(name = "Usuario.findEmailSenha", query = "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha"),
	@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha"),
	@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
	@NamedQuery(name = "Usuario.findByCpf", query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf")
})

public class Usuario implements Serializable{

	private static final long serialVersionUID = -8358244811081225291L;
	
	public static final String BUSCA_EMAIL_SENHA = "Usuario.findEmailSenha";
	public static final String BUSCA_TODOS = "Usuario.findAll";
	public static final String BUSCA_CPF = "Usuario.findByCpf";
	public static final String BUSCA_SENHA = "Usuario.findBySenha";
	public static final String BUSCA_EMAIL = "Usuario.findByEmail";
	
	
	@Id
	@Column(length=20)
	private String cpf;
	
	
	@Column
	private String fraseInspira;
	
	@Column
	private String primeiroNome;
	
	@Column
	private String ultimoNome;
	
	@Column
	private String senha;
	
	@Column(nullable=false) 
	private int perfil;
	
	@Column
	private Calendar dataNascimento;
	
	@Column
	private String sobreMim;
	
	@Column
	private String email;
	
	@Column
	private String caminhoFoto;
	
	@OneToOne(mappedBy = "usuario")
    private Professor professor;
 
	
	@OneToMany(mappedBy="usuario", fetch = FetchType.EAGER)
	private List<Telefone> telefone;
	
	@OneToMany(mappedBy="usuario")
	private List<Endereco> endereco;
	

	@Transient
	private String autentica;
	
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getCpf() {
		return cpf;
	}
	
	

	public void setCpf(String cpf) {
		
		this.cpf  = cpf; 
		
	}
	


	public String getFraseInspira() {
		return fraseInspira;
	}

	public void setFraseInspira(String fraseInspira) {
		this.fraseInspira = fraseInspira;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public String getAutentica() {
		return autentica;
	}



	public void setAutentica(String autentica) {
		this.autentica = autentica;
	}



	public String getSobreMim() {
		return sobreMim;
	}



	public void setSobreMim(String sobreMim) {
		this.sobreMim = sobreMim;
	}



	public List<Telefone> getTelefone() {
		return telefone;
	}



	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}



	public List<Endereco> getEndereco() {
		return endereco;
	}



	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}



	public String getCaminhoFoto() {
		return caminhoFoto;
	}



	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}

}