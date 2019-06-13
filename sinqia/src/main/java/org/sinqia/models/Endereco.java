package org.sinqia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEndereco;
	private String rua;
	private Integer numero;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;
	
	public Endereco() {
		super();
	}
	
	

	public int getIdEndereco() {
		return idEndereco;
	}



	public void setIdEndereco(int idEndereco) {
		this.idEndereco = idEndereco;
	}



	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco + ", rua=" + rua + ", numero=" + numero + ", bairro=" + bairro
				+ ", cep=" + cep + ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	
	
	

}
