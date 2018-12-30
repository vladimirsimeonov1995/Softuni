package bookshopsystem.controller;

import bookshopsystem.service.authorservice.AuthorService;
import bookshopsystem.service.bookservice.BookService;
import bookshopsystem.service.categoryservice.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDB();
        // bookTitles();
        // authorNames();
        //authorsBooksCount();
        booksByAuthorNames();


    }

    private void booksByAuthorNames() {
        bookService.getBooksByAuthor("George", "Powell").forEach(System.out::println);
    }

    private void authorsBooksCount() {
        bookService.getAllAuthorsBooksCount().forEach(System.out::println);
    }


    private void seedDB() throws IOException {
        authorService.seedAuthors();
        categoryService.seedCategories();
        bookService.seedBooks();
    }

    private void bookTitles() {
        List<String> bookTitles = bookService.getAllBooksTitlesAfter(LocalDate.parse("2000-12-31"));

        System.out.println(String.join("\r\n", bookTitles));
    }

    private void authorNames() {
        bookService
                .getAllAuthorsWithBookBefore(LocalDate.parse("2000-01-01"))
                .forEach(System.out::println);
    }
}
