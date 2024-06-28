package kz.logitex.lab.animalCare360.controller;

import kz.logitex.lab.animalCare360.entity.Animal;
import kz.logitex.lab.animalCare360.entity.HealthRecord;
import kz.logitex.lab.animalCare360.service.AnimalServiceImpl;
import kz.logitex.lab.animalCare360.service.HealthRecordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/animals")
public class AnimalController {
    private final AnimalServiceImpl animalService;
    private final HealthRecordServiceImpl healthRecordService;

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.addAnimal(animal);
        return ResponseEntity.ok(createdAnimal);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        if (animals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id) {
        Animal animal = animalService.getAnimal(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.updateAnimal(id, animal);
        if (updatedAnimal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAnimal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/health")
    public ResponseEntity<HealthRecord> addHealthRecord(@PathVariable Long id, @RequestBody HealthRecord healthRecord) {
        Animal animal = animalService.getAnimal(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        healthRecord.setAnimal(animal);
        healthRecord.setDate(new Date());
        HealthRecord createdHealthRecord = healthRecordService.addHealthRecord(healthRecord);
        return ResponseEntity.ok(createdHealthRecord);
    }

    @GetMapping("/{id}/health")
    public ResponseEntity<List<HealthRecord>> getHealthRecords(@PathVariable Long id) {
        List<HealthRecord> healthRecords = healthRecordService.getHealthRecordsByAnimalId(id);
        if (healthRecords.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(healthRecords);
    }

    @PutMapping("/{id}/health")
    public ResponseEntity<HealthRecord> updateHealthRecord(@PathVariable Long id, @RequestBody HealthRecord healthRecord) {
        HealthRecord updatedHealthRecord = healthRecordService.updateHealthRecord(id, healthRecord);
        if (updatedHealthRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedHealthRecord);
    }

    @PutMapping("/{animalId}/user/{userId}")
    public ResponseEntity<Animal> assignUserToAnimal(@PathVariable Long animalId, @PathVariable Long userId) {
        Animal updatedAnimal = animalService.assignUserToAnimal(animalId, userId);
        if (updatedAnimal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedAnimal);
    }
}
