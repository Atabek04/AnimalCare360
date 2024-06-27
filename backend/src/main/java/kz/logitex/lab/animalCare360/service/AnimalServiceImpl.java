package kz.logitex.lab.animalCare360.service;

import kz.logitex.lab.animalCare360.entity.Animal;
import kz.logitex.lab.animalCare360.entity.User;
import kz.logitex.lab.animalCare360.repository.AnimalRepository;
import kz.logitex.lab.animalCare360.repository.UserRepository;
import kz.logitex.lab.animalCare360.service.interfaces.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;

    @Override
    public Animal getAnimal(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @Override
    public Animal addAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public Animal updateAnimal(Long id, Animal updatedAnimal) {
        Animal animal = animalRepository.findById(id).orElse(null);
        if (animal == null) {
            return null;
        }
        animal.setName(updatedAnimal.getName());
        animal.setAge(updatedAnimal.getAge());
        animal.setBreed(updatedAnimal.getBreed());
        animal.setGender(updatedAnimal.getGender());
        animal.setSpecies(updatedAnimal.getSpecies());
        animal.setOwner(updatedAnimal.getOwner());

        return animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    public Animal assignUserToAnimal(Long animalId, Long userId) {
        Animal animal = animalRepository.findById(animalId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (animal == null || user == null) {
            return null;
        }
        animal.setOwner(user);
        user.getAnimals().add(animal);
        return animalRepository.save(animal);
    }
}
