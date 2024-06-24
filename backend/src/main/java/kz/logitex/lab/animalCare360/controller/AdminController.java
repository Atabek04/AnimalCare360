package kz.logitex.lab.animalCare360.controller;

import kz.logitex.lab.animalCare360.auth.AuthenticationResponse;
import kz.logitex.lab.animalCare360.auth.AuthenticationService;
import kz.logitex.lab.animalCare360.auth.RegisterRequest;
import kz.logitex.lab.animalCare360.entity.Medication;
import kz.logitex.lab.animalCare360.service.MedicationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AuthenticationService authService;
    private final MedicationServiceImpl medicationService;

    @PostMapping("/users")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/medications")
    public Medication addMedication(@RequestBody Medication medication) {
        return medicationService.addMedication(medication);
    }

    @PutMapping("/medications/{id}")
    public Medication updateMedication(@PathVariable Long id, @RequestBody Medication medication) {
        return medicationService.updateMedication(id, medication);
    }

    @DeleteMapping("/medications/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }
}