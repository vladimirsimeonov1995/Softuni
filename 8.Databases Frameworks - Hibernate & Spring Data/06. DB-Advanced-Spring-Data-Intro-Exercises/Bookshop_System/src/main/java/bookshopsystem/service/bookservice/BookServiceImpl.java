package bookshopsystem.service.bookservice;

import bookshopsystem.domain.entities.Author;
import bookshopsystem.domain.entities.Book;
import bookshopsystem.domain.entities.Category;
import bookshopsystem.domain.entities.enums.AgeRestriction;
import bookshopsystem.domain.entities.enums.EditionType;
import bookshopsystem.repository.AuthorRepository;
import bookshopsystem.repository.BookRepository;
import bookshopsystem.repository.CategoryRepository;
import bookshopsystem.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH =
            "C:\\Users\\Vlad\\Desktop\\AdvancedQuerying\\src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedBooks() throws IOException {

        if( bookRepository.count() != 0){
            return;
        }

        String[] bookFileContent =
                fileUtil.getFileContent(BOOKS_FILE_PATH);

        for (String currentBookData : bookFileContent) {

            String[] splitData = currentBookData.split("\\s+");

            Book book = new Book();

            EditionType editionType = EditionType
                    .values()[Integer.parseInt(splitData[0])];

            LocalDate releaseDate = LocalDate
                    .parse(
                            splitData[1],
                            DateTimeFormatter
                                    .ofPattern("d/M/yyyy"));

            AgeRestriction ageRestriction =
                    AgeRestriction
                            .values()[Integer.parseInt(splitData[4])];

            StringBuilder title = new StringBuilder();

            for (int i = 5; i < splitData.length; i++) {
                title.append(splitData[i]).append(" ");
            }


            book.setAuthor(getRandomAuthor());
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(Integer.parseInt(splitData[2]));
            book.setPrice(new BigDecimal(splitData[3]));
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title.toString().trim());
            book.setCategories(getCategories());

            bookRepository.saveAndFlush(book);



        }

    }

    @Override
    public List<String> getAllBooksTitlesAfter(LocalDate date) {
        List<Book> books = bookRepository
                        .findAllByReleaseDateAfter(date);

        return books.stream().map(Book::getTitle).collect(Collectors.toList());

    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore(LocalDate date) {

        List<Book> books = bookRepository.findAllByReleaseDateBefore(date);

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(),
                b.getAuthor().getLastName())).collect(Collectors.toSet());

    }

    @Override
    public List<String> getAllAuthorsBooksCount() {

        Map<Long, Integer> authorsBooksCount = new LinkedHashMap<>();

        List<Book> books = bookRepository.findAll();

        for (Book book : books) {

            Long authorId = book.getAuthor().getId();

            if(authorsBooksCount.containsKey(authorId)){
                authorsBooksCount.put(authorId, authorsBooksCount.get(authorId) + 1);
            }else{
                authorsBooksCount.put(authorId, 1);
            }
        }

        List<String> finalResult = new ArrayList<>();

        authorsBooksCount.entrySet()
                .stream()
                .sorted((a,b) -> Integer.compare(b.getValue(), a.getValue()))
                .forEach(a -> {

                    Author author = authorRepository.findAllById(a.getKey());

                    finalResult.add(String.format("%s %s -> %d books",author.getFirstName(), author.getLastName(), a.getValue() ));
                });

        return finalResult;
    }

    @Override
    public List<String> getBooksByAuthor(String firstName, String lastName) {

        Author author = authorRepository.findAllByFirstNameAndLastName(firstName, lastName);

        List<Book> books = bookRepository.findAllByAuthorOrderByReleaseDateDescTitleAsc(author);

        List<String> result = new ArrayList<>();

        for (Book book : books) {
            result.add(String
                    .format("%s ,released -> %s ,copies -> %d",
                            book.getTitle(),
                            book.getReleaseDate(),
                            book.getCopies()));
        }


        return result;
    }

    private Author getRandomAuthor(){
        Random random = new Random();

        int randomId = random
                .nextInt((int) (authorRepository.count() - 1)) + 1;



        return authorRepository.findAllById((long) randomId);
    }

    private Category getRandomCategory(){
        Random random = new Random();

        int randomId = random
                .nextInt((int) (authorRepository.count() - 1)) + 1;



        return this.categoryRepository.findAllById((long) randomId);
    }

    private Set<Category> getCategories(){

        Random random = new Random();
        Set<Category> categories = new HashSet<>();

        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            categories.add(getRandomCategory());
        }

        return categories;
    }
}
