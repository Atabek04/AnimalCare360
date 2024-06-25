package kz.logitex.lab.animalCare360.service.interfaces;

import kz.logitex.lab.animalCare360.entity.HealthRecord;

import java.util.List;

public interface HealthRecordService {
    HealthRecord addHealthRecord(HealthRecord healthRecord);
    List<HealthRecord> getHealthRecordsByAnimalId(Long id);
    HealthRecord updateHealthRecord(Long id, HealthRecord healthRecord);
}
