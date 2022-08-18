package kyklos.org.user;

import kyklos.org.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("select u from users u where u.name = ?1")
    User findByName(String name);

}

