package org.sinqia.daos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.sinqia.models.Categoria;
import org.sinqia.models.Endereco;
import org.sinqia.models.Evento;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class EventoDao {
	@PersistenceContext
	private EntityManager manager;
	@DateTimeFormat
	private Calendar data = Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00"), new Locale("pt", "BR"));

	public void gravar(Evento evento) {
		manager.persist(evento);
	}
	
	public Evento findById(Integer id) {
		String jpql = "select e from Evento e where e.id = :id";
		return manager.createQuery(jpql, Evento.class).setParameter("id", id).getSingleResult();
	}
	
	public List<Evento> listar(){
		String jpql = "select e from Evento e";
		return manager.createQuery(jpql, Evento.class).getResultList();
	}
	
	public List<Evento> listarPorId(String id) {
		String jpql = "select e from Evento e where e.empresa.cnpj = :id";
		return manager.createQuery(jpql, Evento.class).setParameter("id", id).getResultList();
	}
	
	public Evento findEvento(Integer id) {
		String jpql = "select e from Evento e where e.id = :id";
		return manager.createQuery(jpql, Evento.class).setParameter("id", id).getSingleResult();
	}
	
	public void deletar(Integer id) {
		String jpql = "select e from Evento e where e.id = :id";
		Evento evento = manager.createQuery(jpql, Evento.class).setParameter("id", id).getSingleResult();
		manager.remove(evento);
	}
	
	public void atualizar(Evento evento, Integer id) {
		evento.setId(id);
		manager.merge(evento);
	}
	public List<Evento> eventosDeHoje(){
		String jpql = "select e from Evento e where DAY(e.data) = DAY(NOW()) and "
				+ "									MONTH(e.data) = MONTH(NOW()) and"
				+ "									YEAR(e.data) = YEAR(NOW())"
				+ "									order by e.nome";
		return manager.createQuery(jpql, Evento.class).getResultList();
	}

	public List<Evento> findByName(String nome) {
		String jpql = "select e from Evento e where e.nome like :nome";
		return manager.createQuery(jpql, Evento.class).setParameter("nome", "%" +nome+ "%").getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Evento> searchEvento(Evento evento) throws ParseException {
		Session session = (Session) manager.getDelegate();
		Example example = Example.create(evento);

		List<Evento> eventos;
		if(evento.getCategoria().getId() !=null) {
			if(evento.getEndereco().getBairro().isEmpty()) {
				eventos = session.createCriteria(Evento.class).add(example)
						.add(Restrictions.eq("categoria.id", evento.getCategoria().getId()))
						.createCriteria("endereco")
							.add(Restrictions.eq("cidade", evento.getEndereco().getCidade()))
						.list();
			}else {
				eventos = session.createCriteria(Evento.class).add(example)
						.add(Restrictions.eq("categoria.id", evento.getCategoria().getId()))
						.createCriteria("endereco")
							.add(Restrictions.eq("cidade", evento.getEndereco().getCidade()))
							.add(Restrictions.eq("bairro", evento.getEndereco().getBairro()))
						.list();
			}
		}else {
			if(evento.getEndereco().getBairro().isEmpty()) {
				eventos = session.createCriteria(Evento.class).add(example)
						.createCriteria("endereco")
						.add(Restrictions.eq("cidade", evento.getEndereco().getCidade()))
						.list();
			}else {
				eventos = session.createCriteria(Evento.class).add(example)
						.createCriteria("endereco")
						.add(Restrictions.eq("cidade", evento.getEndereco().getCidade()))
						.add(Restrictions.eq("bairro", evento.getEndereco().getBairro()))
						.list();
			}
		}
		return eventos;
	}

	
	public void mergeEvento(Evento evento) {
		manager.merge(evento);
	}	

}
