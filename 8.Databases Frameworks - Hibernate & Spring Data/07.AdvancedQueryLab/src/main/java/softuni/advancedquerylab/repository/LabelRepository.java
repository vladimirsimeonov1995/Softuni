package softuni.advancedquerylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.advancedquerylab.domain.entities.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

    Label findTopById(Long id);

}
