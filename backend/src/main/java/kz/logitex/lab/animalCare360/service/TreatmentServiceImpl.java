package kz.logitex.lab.animalCare360.service;

import kz.logitex.lab.animalCare360.entity.Animal;
import kz.logitex.lab.animalCare360.entity.Treatment;
import kz.logitex.lab.animalCare360.repository.AnimalRepository;
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

    @Override
    public Treatment addTreatment(Long animalId, Treatment treatment) {
        Optional<Animal> animal = animalRepository.findById(animalId);
        if (animal.isPresent()) {
            if (treatment.getMedication() == null) {
                throw new IllegalArgumentException("Medication cannot be null");
            }

            treatment.setAnimal(animal.get());
            return treatmentRepository.save(treatment);
        }
        throw new RuntimeException("Animal not found");
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
