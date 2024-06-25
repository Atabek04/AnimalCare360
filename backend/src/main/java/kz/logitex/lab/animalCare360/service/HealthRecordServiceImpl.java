package kz.logitex.lab.animalCare360.service;

import kz.logitex.lab.animalCare360.entity.HealthRecord;
import kz.logitex.lab.animalCare360.repository.HealthRecordRepository;
import kz.logitex.lab.animalCare360.service.interfaces.HealthRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthRecordServiceImpl implements HealthRecordService {
    private final HealthRecordRepository healthRecordRepository;

    public HealthRecord addHealthRecord(HealthRecord healthRecord) {
        return healthRecordRepository.save(healthRecord);
    }

    public List<HealthRecord> getHealthRecordsByAnimalId(Long animalId) {
        return healthRecordRepository.findByAnimalId(animalId);
    }

    public HealthRecord updateHealthRecord(Long id, HealthRecord healthRecord) {
        healthRecord.setId(id);
        return healthRecordRepository.save(healthRecord);
    }
}
