package kz.logitex.lab.animalCare360.repository;

import kz.logitex.lab.animalCare360.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
