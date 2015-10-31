package ua.lviv.lgs.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.lviv.lgs.dao.AuthorDao;
import ua.lviv.lgs.domain.Author;

@Repository
public class AuthorDaoImpl implements AuthorDao {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void insert(Author author) {
		em.persist(author);
	}

	@Transactional
	public List<Author> getAll() {
		return em.createQuery("From Author").getResultList();
	}

	@Transactional
	public List<Author> getByName(String name) {
		return em.createNamedQuery("Author.findByName")
				.setParameter("name", name).getResultList();
	}

}
