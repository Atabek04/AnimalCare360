package kz.logitex.lab.animalCare360.service.interfaces;

import kz.logitex.lab.animalCare360.entity.Medication;

import java.util.List;

public interface MedicationService {
    Medication addMedication(Medication medication);
    Medication updateMedication(Long id, Medication medication);
    void deleteMedication(Long id);
    Medication getMedication(Long id);
    List<Medication> getAllMedications();
}
