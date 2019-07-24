package jspexercises.repository.userrepo;

import jspexercises.domain.entities.User;
import jspexercises.repository.GenericRepository;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);
}
