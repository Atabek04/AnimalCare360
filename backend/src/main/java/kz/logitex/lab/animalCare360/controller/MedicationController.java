package kz.logitex.lab.animalCare360.controller;

import kz.logitex.lab.animalCare360.entity.Medication;
import kz.logitex.lab.animalCare360.entity.Treatment;
import kz.logitex.lab.animalCare360.service.MedicationServiceImpl;
import kz.logitex.lab.animalCare360.service.TreatmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Medication>> getAllMedications() {
        List<Medication> medications = medicationService.getAllMedications();
        if (medications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medications);
    }

    @PostMapping("/animals/{animalId}/medications/{medicationId}")
    public ResponseEntity<Treatment> linkMedicationToAnimal(@PathVariable Long animalId,
                                                            @PathVariable Long medicationId,
                                                            @RequestBody Treatment treatment) {
        Treatment createdTreatment = treatmentService.addTreatment(animalId, medicationId, treatment);
        if (createdTreatment.getMedication() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(createdTreatment);
    }

    @GetMapping("/animals/{animalId}/medications")
    public ResponseEntity<List<Treatment>> getMedicationHistoryForAnimal(@PathVariable Long animalId) {
        List<Treatment> treatments = treatmentService.getTreatmentsForAnimal(animalId);
        if (treatments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(treatments);
    }

    @PutMapping("/animals/{animalId}/medications")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long animalId, @RequestBody Treatment treatment) {
        Treatment updatedTreatment = treatmentService.updateTreatment(animalId, treatment);
        if (updatedTreatment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTreatment);
    }
}
