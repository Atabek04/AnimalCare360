package kz.logitex.lab.animalCare360.service;

import kz.logitex.lab.animalCare360.entity.User;
import kz.logitex.lab.animalCare360.repository.UserRepository;
import kz.logitex.lab.animalCare360.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByEmail(username).orElse(null);
    }

    @Override
    public User updateUser(String username, User updatedUser) {
        User user = userRepository.findByEmail(username).orElse(null);
        if (user == null) {
            return null;
        }
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
