package bookshopsystem.repository;

import bookshopsystem.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findAllById(Long id);

    Author findAllByFirstNameAndLastName(String firstName, String lastName);

}
