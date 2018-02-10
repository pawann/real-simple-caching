package org.pn.real.simple.service;

import org.pn.real.simple.model.Book;
import org.springframework.stereotype.Component;

@Component("BookService")
public class BookService implements IBookService {

    @Override
    public Book getBook(String isbn) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setAuthor("A random author of " + isbn);
        book.setTitle("A random title of " + isbn);
        return book;
    }
}
