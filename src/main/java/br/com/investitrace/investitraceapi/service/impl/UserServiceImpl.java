package br.com.investitrace.investitraceapi.service.impl;

import br.com.investitrace.investitraceapi.domain.model.User;
import br.com.investitrace.investitraceapi.domain.repository.UserRepository;
import br.com.investitrace.investitraceapi.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(User user) {
        validateUserDoesNotExist(user);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        User existingUser = getUserOrThrow(user.getId());

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(Long userId) {
        return getUserOrThrow(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    private void validateUserDoesNotExist(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User already exists with id: " + user.getId());
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User already exists with email: " + user.getEmail());
        }
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
}
