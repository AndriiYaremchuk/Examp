package ua.lviv.lgs.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ua.lviv.lgs.dao.BookDao;
import ua.lviv.lgs.domain.Book;

@Repository
public class BookDaoImpl implements BookDao {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void createBook(Book book) {
		em.persist(book);
	}
	public void getBooks (){
		em.createQuery("Select from Author");
	}
}
