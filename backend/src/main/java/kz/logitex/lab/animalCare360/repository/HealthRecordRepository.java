package kz.logitex.lab.animalCare360.repository;

import kz.logitex.lab.animalCare360.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByAnimalId(Long animalId);
}
