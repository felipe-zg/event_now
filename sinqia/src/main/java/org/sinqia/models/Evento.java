package org.sinqia.models;


import java.text.SimpleDateFormat;
import java.util.Calendar;



import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evento {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Empresa empresa = new Empresa();
	private String nome;
	@ManyToOne(fetch=FetchType.EAGER)
	private Categoria categoria;
	@OneToOne(fetch=FetchType.EAGER)
	private Endereco endereco;
	private Double valor;
	@DateTimeFormat
	private Calendar data;
	private String hora;
	
	public String dataToString() {
		if(this.data != null){
			SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy"); 
			String a = s.format(this.data.getTime()); 
			return a;
		}
		return null;
	}

	public Evento() {
		super();
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Double getValor() {
		return valor;
	}


	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Calendar getData() {
		return data;
	}


	public void setData(Calendar data) {
		this.data = data;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	public Empresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	


	@Override
	public String toString() {
		return "Evento [id=" + id + ", empresa=" + empresa + ", nome=" + nome + ", categoria=" + categoria
				+ ", endereco=" + endereco + ", valor=" + valor + ", data=" + data + ", hora=" + hora + "]";
	}
	
	
	
	
}
