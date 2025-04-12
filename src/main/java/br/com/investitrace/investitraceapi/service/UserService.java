package br.com.investitrace.investitraceapi.service;

import br.com.investitrace.investitraceapi.domain.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    void updateUser(User user);

    void deleteUser(Long userId);

    User getUserById(Long userId);

    List<User> getAllUsers();
}
