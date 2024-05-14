package co.edu.eci.cvds.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository {
=======
import co.edu.eci.cvds.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
>>>>>>> feature_private
}
