package kz.logitex.lab.animalCare360.service.interfaces;

import kz.logitex.lab.animalCare360.entity.Treatment;

import java.util.List;

public interface TreatmentService {
    Treatment addTreatment(Long animalId, Long medicationId, Treatment treatment);
    List<Treatment> getTreatmentsForAnimal(Long animalId);
    Treatment updateTreatment(Long animalId, Treatment treatment);
}
