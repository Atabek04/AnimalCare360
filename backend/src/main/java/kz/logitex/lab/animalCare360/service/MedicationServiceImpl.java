package kz.logitex.lab.animalCare360.service;

import kz.logitex.lab.animalCare360.entity.Medication;
import kz.logitex.lab.animalCare360.repository.MedicationRepository;
import kz.logitex.lab.animalCare360.service.interfaces.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;

    @Override
    public Medication addMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    @Override
    public Medication updateMedication(Long id, Medication medication) {
        Medication medicationToUpdate = medicationRepository.findById(id).orElse(null);
        if (medicationToUpdate == null) {
            return null;
        }
        medicationToUpdate.setName(medication.getName());
        medicationToUpdate.setDescription(medication.getDescription());
        medicationToUpdate.setManufacturer(medication.getManufacturer());
        return medicationRepository.save(medicationToUpdate);
    }

    @Override
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }

    @Override
    public Medication getMedication(Long id) {
        return medicationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }
}
