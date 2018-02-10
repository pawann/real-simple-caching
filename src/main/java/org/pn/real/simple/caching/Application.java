package org.pn.real.simple.caching;

import java.util.Random;

import org.pn.real.simple.service.BookService;
import org.pn.real.simple.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StopWatch;

public class Application {

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(ApplicationConfig.class, args);
        IBookService bookService = ctx.getBean(IBookService.class);

        Integer booksCount = null;
        Integer accessCount = null;
        try {
            booksCount = Integer.parseInt(args[0]);
            accessCount = Integer.parseInt(args[1]);
        } catch(Exception ex) {
            LOGGER.info("Invalid or missing number of Books & access count. Defaults will be used.");
            booksCount = 5;
            accessCount = 1000;
        }

        LOGGER.info("Number of books {} to be accessed {} times.", booksCount, accessCount);

        run(bookService, booksCount, accessCount);
    }

    /**
     * Simulated retrieval of books
     * 
     * @param service
     * @param args
     * @throws Exception
     */
    public static void run(IBookService service, Integer booksCount, Integer accessCount) throws Exception {

        Random randBookIsbn = new Random();
        StopWatch watch = new StopWatch();
        watch.start();
        for(int i = 0; i < accessCount; i++) {
            String isbn = "" + (randBookIsbn.nextInt(booksCount) + 1);
            LOGGER.info("Retrieving book for isbn {} ....", isbn);
            service.getBook(isbn);
            LOGGER.info("complete");
        }
        watch.stop();
        LOGGER.info("took {}ms to access {} books {} times", watch.getTotalTimeMillis(), booksCount, accessCount);
        LOGGER.info("Given that each access takes {} second (without caching)! This is pretty good. ",
            (BookService.THIS_HOW_LONG_LIBRARIAN_TAKES / 1000));

    }
}
