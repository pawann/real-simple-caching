package org.pn.real.simple.service;

import org.pn.real.simple.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component("BookService")
public class BookService implements IBookService {

    public static final long THIS_HOW_LONG_LIBRARIAN_TAKES = 2000L;

    private static Logger LOGGER = LoggerFactory.getLogger(BookService.class);

    @Override
    // @CacheResult(cacheName = "books")
    @Cacheable("books")
    public Book getBook(String isbn) {
        LOGGER.info("Hold on! Librarian is getting the book {}. (No comfort from caching!)", isbn);
        doTimePass();
        Book book = new Book();
        book.setIsbn(isbn);
        book.setAuthor("A random author of " + isbn);
        book.setTitle("A random title of " + isbn);

        return book;
    }

    /**
     * Acts like a slow librarian
     */
    private void doTimePass() {
        try {
            Thread.sleep(THIS_HOW_LONG_LIBRARIAN_TAKES);
        } catch(InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
