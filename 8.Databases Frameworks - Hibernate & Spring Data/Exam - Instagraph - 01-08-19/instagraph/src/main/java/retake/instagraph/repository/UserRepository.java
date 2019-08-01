package retake.instagraph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retake.instagraph.domain.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query("" +
            "SELECT u " +
            "FROM retake.instagraph.domain.entities.User u " +
            "JOIN u.posts p " +
            "GROUP BY u " +
            "ORDER BY size(u.posts) DESC , u.id")
    List<User> exportUsersWithPosts();

}
