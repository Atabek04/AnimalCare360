package kz.logitex.lab.animalCare360.service.interfaces;

import kz.logitex.lab.animalCare360.entity.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    List<User> getAllUsers();

    User updateUser(String username, User updatedUser);
    void deleteUser(Long id);
}
