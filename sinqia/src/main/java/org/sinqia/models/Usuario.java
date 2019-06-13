package org.sinqia.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Usuario {
	@Id
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Evento> eventos;

	public Usuario() {}
	public Usuario(String cpf, String nome, String email) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
	}
	
	public Boolean addEvento(Evento evento) {
		if(this.eventos == null)
			this.eventos = new ArrayList<Evento>();
		
		Boolean hasEvento = false;
		for (Evento e : eventos) {
			if(e.getId() == evento.getId()) {
				hasEvento = true;
				break;
			}
		}
		if(!hasEvento) {
			this.eventos.add(evento);
			return true;
		}
		return false;
	}
	
	public void removeEvento(Evento evento) {
		Evento ev = null;
		for (Evento e : eventos) {
			if(e.getId() == evento.getId()) 
				ev = e;
		}
		this.eventos.remove(ev);
	}
	
	// MÉTODO QUE CONFIRMA UM EVENTO E O COLOCA NA LISTA DE CONFIRMADOS DO USUÁRIO
//	public Boolean confirmaEvento(Evento evento) {
//		if(this.idEventosConfirmados == null)
//			this.idEventosConfirmados = new ArrayList<Integer>();
//		
//		Boolean hasEvento = false;
//		for (Integer id : idEventosConfirmados) {
//			if(id == evento.getId()) {
//				hasEvento = true;
//				break;
//			}
//		}
//		if(!hasEvento) {
//			this.idEventosConfirmados.add(evento.getId());
//			return true;
//		}
//		return false;
//	}
//	
	
	
	
	

	

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	public List<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
}
