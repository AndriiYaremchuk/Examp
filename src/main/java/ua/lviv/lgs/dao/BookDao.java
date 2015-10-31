package ua.lviv.lgs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ua.lviv.lgs.domain.Author;
import ua.lviv.lgs.domain.Book;

public interface BookDao {

	/* create new instance */
	void createBook(Book book);
}
