package csw.t1.csw.repositories;

import csw.t1.csw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
