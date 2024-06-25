package kz.logitex.lab.animalCare360.controller;

import kz.logitex.lab.animalCare360.entity.Animal;
import kz.logitex.lab.animalCare360.entity.HealthRecord;
import kz.logitex.lab.animalCare360.service.AnimalServiceImpl;
import kz.logitex.lab.animalCare360.service.HealthRecordServiceImpl;
import lombok.RequiredArgsConstructor;
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
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.addAnimal(animal);
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public Animal getAnimal(@PathVariable Long id) {
        return animalService.getAnimal(id);
    }

    @PutMapping("/{id}")
    public Animal updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.updateAnimal(id, animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }

    @PostMapping("/{id}/health")
    public HealthRecord addHealthRecord(@PathVariable Long id, @RequestBody HealthRecord healthRecord) {
        healthRecord.setAnimal(animalService.getAnimal(id));
        healthRecord.setDate(new Date());
        return healthRecordService.addHealthRecord(healthRecord);
    }

    @GetMapping("/{id}/health")
    public List<HealthRecord> getHealthRecords(@PathVariable Long id) {
        return healthRecordService.getHealthRecordsByAnimalId(id);
    }

    @PutMapping("/{id}/health")
    public HealthRecord updateHealthRecord(@PathVariable Long id, @RequestBody HealthRecord healthRecord) {
        return healthRecordService.updateHealthRecord(id, healthRecord);
    }
}













