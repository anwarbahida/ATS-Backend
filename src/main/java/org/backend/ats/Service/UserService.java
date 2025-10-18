package org.backend.ats.service;

import org.backend.ats.model.User;
import org.backend.ats.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // On peut aussi normaliser l'email ici
        user.setEmail(user.getEmail().trim().toLowerCase());
        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        if (username == null || password == null) {
            throw new RuntimeException("Nom d'utilisateur ou mot de passe manquant");
        }

        username = username.trim();

        User existingUser = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!existingUser.getPassword().equals(password)) {
            throw new RuntimeException("Mot de passe incorrect");
        }

        return existingUser;
    }
}
