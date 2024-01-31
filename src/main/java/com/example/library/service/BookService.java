package com.example.library.service;

import java.util.List;
import java.util.Optional;

import com.example.library.model.Book;

public interface BookService {
	
	List<Book> showAllBooks();
	Optional<Book> searchBook(int id);
	Book addNewBook(Book book);
	void deleteBook(Integer bok);

}
