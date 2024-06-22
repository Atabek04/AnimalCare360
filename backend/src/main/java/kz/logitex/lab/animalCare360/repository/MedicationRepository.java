package kz.logitex.lab.animalCare360.repository;

import kz.logitex.lab.animalCare360.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
