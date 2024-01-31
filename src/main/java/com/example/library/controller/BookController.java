package com.example.library.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.library.model.Book;
import com.example.library.service.BookService;

@Controller
public class BookController {
@Autowired
private BookService bookservice;
	
	@GetMapping("/")
	public String homePage() 
	{
		return "index";
	}
	@GetMapping("/view")
	public String viewPage(Model model)
	{
		model.addAttribute("allBooks", bookservice.showAllBooks());
		return "view";
	}
	@GetMapping("/add")
	public String addPage(Model model)
	{
		model.addAttribute("obj",new Book());
		return "add";
	}
	
	@PostMapping("/add")
	public String addBook(@ModelAttribute("obj") Book book)
	{
		Optional<Book> opt=bookservice.searchBook(book.getBook_id());
		if(opt.isEmpty())
		{
			bookservice.addNewBook(book);
			return "redirect:/view";
		}
		else
		{
			return "redirect:/add?failed";
		}
	}
	@GetMapping("/edit/{id}")                            
	public String editPage(@PathVariable("id")Integer bok, Model model)             // the bok is a new variable created for pojo id 
	{
		model.addAttribute("obj", bookservice.searchBook(bok));
		return "/edit";                                 
	}
	@PostMapping("/edit")
	public String editBike(@ModelAttribute("obj") Book book)           // the Bike bike is converting the variable into local once
	{
		bookservice.addNewBook(book);
		return "redirect:/view";                       // this statement will be same as in controller line 49 becoz only edit is going so new methods
	}
	
	@GetMapping("/delete/{id}")
	public String deletePage(@PathVariable("id") Integer bok)
	{
		bookservice.deleteBook(bok);
		return "redirect:/view";
	}


}

