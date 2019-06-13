package org.sinqia.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Empresa {
	@Id
	private String cnpj;
	private String nomeFantasia;
	@NotNull 
	private String email;
	private String senha;
	@OneToOne
	private Endereco endereco;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Endereco> enderecosAlternativos;

	
	public Empresa() {
		super();
	}
	
	public Empresa(String cnpj, String nomeFantasia, String email, String senha, Endereco endereco) {
		super();
		this.cnpj = cnpj;
		this.nomeFantasia = nomeFantasia;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getNomeFantasia() {
		return nomeFantasia;
	}


	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public List<Endereco> getEnderecosAlternativos() {
		return enderecosAlternativos;
	}

	public void setEnderecosAlternativos(List<Endereco> enderecosAlternativos) {
		this.enderecosAlternativos = enderecosAlternativos;
	}

	@Override
	public String toString() {
		return "Empresa [cnpj= " + cnpj + ", nomeFantasia= " + this.getNomeFantasia() + ", email= " + email + " ]";
	}

	public void addEnderecoAlternativo(Endereco endereco) {
		if(this.enderecosAlternativos == null)
			this.enderecosAlternativos = new ArrayList<Endereco>();
		this.enderecosAlternativos.add(endereco);
	}
}
