package kz.logitex.lab.animalCare360.service;

import kz.logitex.lab.animalCare360.entity.Animal;
import kz.logitex.lab.animalCare360.entity.Medication;
import kz.logitex.lab.animalCare360.entity.Treatment;
import kz.logitex.lab.animalCare360.repository.AnimalRepository;
import kz.logitex.lab.animalCare360.repository.MedicationRepository;
import kz.logitex.lab.animalCare360.repository.TreatmentRepository;
import kz.logitex.lab.animalCare360.service.interfaces.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {
    private final AnimalRepository animalRepository;
    private final TreatmentRepository treatmentRepository;
    private final MedicationRepository medicationRepository;

    @Override
    public Treatment addTreatment(Long animalId, Long medicationId, Treatment treatment) {
        Optional<Animal> animal = animalRepository.findById(animalId);
        Optional<Medication> medication = medicationRepository.findById(medicationId);
        if (animal.isPresent() && medication.isPresent()) {
            treatment.setAnimal(animal.get());
            treatment.setMedication(medication.get());
            return treatmentRepository.save(treatment);
        }
        throw new RuntimeException("Animal or Medication not found");
    }

    @Override
    public List<Treatment> getTreatmentsForAnimal(Long animalId) {
        return treatmentRepository.findByAnimalId(animalId);
    }

    @Override
    public Treatment updateTreatment(Long animalId, Treatment treatment) {
        Optional<Animal> animal = animalRepository.findById(animalId);
        if (animal.isPresent()) {
            treatment.setAnimal(animal.get());
            return treatmentRepository.save(treatment);
        }
        throw new RuntimeException("Animal not found");
    }
}
