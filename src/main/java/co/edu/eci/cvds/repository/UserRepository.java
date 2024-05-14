package co.edu.eci.cvds.repository;

import co.edu.eci.cvds.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
