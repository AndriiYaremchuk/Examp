package ua.lviv.lgs.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.AuthorDao;
import ua.lviv.lgs.dao.BookDao;
import ua.lviv.lgs.domain.Author;
import ua.lviv.lgs.domain.Book;
import ua.lviv.lgs.service.AuthorService;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	private AuthorDao dao;
	@Autowired
	private BookDao bDao;
	
	public void insertAuthor(Author author, Book book) {
		Book b = new Book ("Hello World");
		dao.insert(author);
		bDao.createBook(b);
	}

	public List<Author> getAllAuthors() {
		
		return dao.getAll();
	}

	public List<Author> getAuthorByName(String name) {
		
		return dao.getByName(name);
	}

	public void insertAuthor(Author author) {
		// TODO Auto-generated method stub
		
	}

}
