package ua.lviv.lgs.service;

import java.util.List;

import ua.lviv.lgs.domain.Author;

public interface AuthorService {
	void insertAuthor(Author author);
	
	List <Author> getAllAuthors();
	
	List <Author> getAuthorByName(String name);
}
