package bookshopsystem.service.bookservice;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter(LocalDate date);

    Set<String> getAllAuthorsWithBookBefore(LocalDate date);

    List<String> getAllAuthorsBooksCount();

    List<String> getBooksByAuthor(String firstName, String lastName);

}
