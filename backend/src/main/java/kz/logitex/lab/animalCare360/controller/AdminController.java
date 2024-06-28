package kz.logitex.lab.animalCare360.controller;

import kz.logitex.lab.animalCare360.auth.AuthenticationResponse;
import kz.logitex.lab.animalCare360.auth.AuthenticationService;
import kz.logitex.lab.animalCare360.auth.RegisterRequest;
import kz.logitex.lab.animalCare360.entity.Medication;
import kz.logitex.lab.animalCare360.entity.User;
import kz.logitex.lab.animalCare360.service.MedicationServiceImpl;
import kz.logitex.lab.animalCare360.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AuthenticationService authService;
    private final MedicationServiceImpl medicationService;
    private final UserServiceImpl userService;

    @PostMapping("/users")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/medications")
    public ResponseEntity<Medication> addMedication(@RequestBody Medication medication) {
        Medication createdMedication = medicationService.addMedication(medication);
        return ResponseEntity.ok(createdMedication);
    }

    @PutMapping("/medications/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable Long id, @RequestBody Medication medication) {
        Medication updatedMedication = medicationService.updateMedication(id, medication);
        if (updatedMedication == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMedication);
    }

    @DeleteMapping("/medications/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
}
