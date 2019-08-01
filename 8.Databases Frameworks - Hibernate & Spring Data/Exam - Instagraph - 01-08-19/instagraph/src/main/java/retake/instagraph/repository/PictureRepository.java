package retake.instagraph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retake.instagraph.domain.entities.Picture;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

    Optional<Picture> findByPath(String path);

    List<Picture> findBySizeAfterOrderBySizeAsc(Double size);

}
