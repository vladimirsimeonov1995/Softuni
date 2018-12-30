package bookshopsystem.service.authorservice;

import bookshopsystem.domain.entities.Author;
import bookshopsystem.repository.AuthorRepository;
import bookshopsystem.service.authorservice.AuthorService;
import bookshopsystem.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH =
            "C:\\Users\\Vlad\\Desktop\\AdvancedQuerying\\src\\main\\resources\\files\\authors.txt";

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {

        if(this.authorRepository.count() != 0){
            return;
        }

        String[] authorFileContent =
                fileUtil.getFileContent(AUTHORS_FILE_PATH);

        for (String currentAuthorData : authorFileContent) {
            String[] names = currentAuthorData.split("\\s+");

            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);

            authorRepository.saveAndFlush(author);
        }

    }
}
