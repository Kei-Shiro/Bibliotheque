package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    ResponseEntity<User> getUserById(Long id);

    User createUser(User user);

    ResponseEntity<User> updateUser(Long id, User updatedUser);

    ResponseEntity<Void> deleteUser(Long id);
}
