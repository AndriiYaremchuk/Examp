package ua.lviv.lgs.controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.lgs.domain.Author;
import ua.lviv.lgs.service.AuthorService;

@Controller
public class AuthorController {
	@Autowired
	private AuthorService service;

	/* Selects all Authors from database */
	@RequestMapping(value = "/showAll")
	public String getAllAuthors(Model model) {
		List<Author> authors = service.getAllAuthors();
		model.addAttribute("authors", authors);
		return "authors";
	}

	/* Selects all Authors from DB, whose age is under 40 or other preset */
	@RequestMapping(value = "/youngAuthors")
	public String youngAuthors(Model model) {
		List<Author> authors = service.getAllAuthors();
		List<Author> result = new ArrayList<Author>();
		for (Author a : authors) {
			if (a.getAge() <= 40)
				result.add(a);
		}
		model.addAttribute("authors", result);
		return "authors";
	}

	/* Selects all Authors with required age */
	@RequestMapping(value = "/AuthorsByAge")
	public String agedAuthors(Model model,
			@RequestParam(value = "age", required = false) String age) {
		List<Author> authors = service.getAllAuthors();
		List<Author> result = new ArrayList<Author>();
		int ageInt = Integer.parseInt(age);
		for (Author a : authors) {
			if (a.getAge() == ageInt)
				result.add(a);
		}
		model.addAttribute("authors", result);
		return "authors";
	}

	/* Selects Authors by preset name or age */
	@RequestMapping(value = "/AuthorsbyAgeOrByName")
	public String agedAuthors(Model model,
			@RequestParam(value = "age", required = false) String age,
			@RequestParam(value = "name", required = false) String name) {

		List<Author> authors = service.getAllAuthors();
		List<Author> result = new ArrayList<Author>();

		int ageInt = Integer.parseInt(age);

		for (Author a : authors) {
			if (age != null && age != "") {
				if (a.getAge() == ageInt)
					result.add(a);
			} else if (name != null && name != "") {
				if (a.getName().equalsIgnoreCase(name))
					result.add(a);
			}
		}
		model.addAttribute("authors", result);
		return "authors";
	}

	/*
	 * Selects all Authors with such name and prevent from selecting
	 * unidentified Author
	 */
	@RequestMapping(value = "/filterAuthor")
	public String agedAuthors(
			Model model,
			@RequestParam(value = "age", required = false, defaultValue = "-1") int age,
			@RequestParam(value = "name", required = false) String name) {

		List<Author> authors = service.getAllAuthors();

		Iterator<Author> iter = authors.iterator();
		while (iter.hasNext()) {
			Author currentAuthor = iter.next();
			if (currentAuthor.getName().equalsIgnoreCase(name)
					&& currentAuthor.getAge() != age) {
				iter.remove();
			}
		}
		model.addAttribute("authors", authors);
		return "authors";
	}

	/* Creates new Author instance */
	@RequestMapping(value = "/createAuthor")
	public String createPage() {
		return "newAuthor";
	}

	/* Another variation of protected Author-by-name selection */
	@RequestMapping(value = "/showAll", method = RequestMethod.POST)
	public String creatingAuthor(Model model,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "age") String age) {
		int ageInt = Integer.parseInt(age);
		if (name.length() >= 3 && ageInt >= 5) {
			Author a = new Author(name, ageInt);
			service.insertAuthor(a);
			return "redirect:/showAll";
		} else
			throw new InvalidParameterException(
					"Name or age cannot be this short");

	}
}
