package kz.logitex.lab.animalCare360.controller;

import kz.logitex.lab.animalCare360.entity.Medication;
import kz.logitex.lab.animalCare360.entity.Treatment;
import kz.logitex.lab.animalCare360.service.MedicationServiceImpl;
import kz.logitex.lab.animalCare360.service.TreatmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MedicationController {
    private final MedicationServiceImpl medicationService;
    private final TreatmentServiceImpl treatmentService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_VETERINARIAN')")
    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }

    @PostMapping("/animals/{animalId}/medications")
    public Treatment linkMedicationToAnimal(@PathVariable Long animalId, @RequestBody Treatment treatment) {
        if (treatment.getMedication() == null) {
            throw new RuntimeException("Medication is required");
        }
        return treatmentService.addTreatment(animalId, treatment);
    }

    @GetMapping("/animals/{animalId}/medications")
    public List<Treatment> getMedicationHistoryForAnimal(@PathVariable Long animalId) {
        return treatmentService.getTreatmentsForAnimal(animalId);
    }

    @PutMapping("/animals/{animalId}/medications")
    public Treatment updateTreatment(
            @PathVariable Long animalId,
            @RequestBody Treatment treatment) {
        return treatmentService.updateTreatment(animalId, treatment);
    }
}
