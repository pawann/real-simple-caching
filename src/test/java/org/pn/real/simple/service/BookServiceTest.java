package org.pn.real.simple.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pn.real.simple.caching.Application;
import org.pn.real.simple.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class BookServiceTest {

    @Autowired
    private IBookService bookService;

    @Test
    public void testGetBook() {
        String isbn = "1";
        Book book = bookService.getBook(isbn);
        Assert.assertNotNull(book);
        Assert.assertEquals(isbn, book.getIsbn());
    }

    @Ignore("Ignored until caching is implemented")
    @Test
    public void testGetBookCaching() {
        String isbn = "1";
        Book book1 = bookService.getBook(isbn);
        Assert.assertNotNull(book1);
        Assert.assertEquals(isbn, book1.getIsbn());

        Book book2 = bookService.getBook(isbn);
        Assert.assertNotNull(book2);
        Assert.assertEquals(isbn, book2.getIsbn());

        // Caching test
        Assert.assertEquals(book2, book1);

    }

}
