package kz.logitex.lab.animalCare360.repository;

import kz.logitex.lab.animalCare360.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long>{
}
