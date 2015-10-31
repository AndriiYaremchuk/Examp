package ua.lviv.lgs.dao;

import java.util.List;

import ua.lviv.lgs.domain.Author;

public interface AuthorDao {
	void insert(Author author);

	/* To get all Authors from DB */
	List<Author> getAll();

	/* Select Author by name */
	List<Author> getByName(String name);
}
