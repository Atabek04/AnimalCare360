package kz.logitex.lab.animalCare360.service.interfaces;

import kz.logitex.lab.animalCare360.entity.Animal;

import java.util.List;

public interface AnimalService {
    Animal getAnimal(Long id);
    List<Animal> getAllAnimals();
    Animal addAnimal(Animal animal);
    Animal updateAnimal(Long id, Animal updatedAnimal);
    void deleteAnimal(Long id);
}
