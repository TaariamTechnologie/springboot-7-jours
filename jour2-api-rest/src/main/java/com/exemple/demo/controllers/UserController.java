package com.exemple.demo.controllers;

import com.exemple.demo.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>(
        Arrays.asList(
            new User(1L, "Alice", "alice@email.com"),
            new User(2L, "Bob", "bob@email.com")
        )
    );

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        users.add(user);
        return "Utilisateur ajouté avec succès.";
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return "Utilisateur mis à jour.";
            }
        }
        return "Utilisateur introuvable.";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
        return "Utilisateur supprimé.";
    }
}