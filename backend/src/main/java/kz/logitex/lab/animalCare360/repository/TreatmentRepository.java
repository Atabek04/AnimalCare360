package kz.logitex.lab.animalCare360.repository;

import kz.logitex.lab.animalCare360.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long>{
    List<Treatment> findByAnimalId(Long animalId);
}
