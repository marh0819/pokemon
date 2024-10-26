package co.edu.uceva.pokemon.services.impl;

import co.edu.uceva.pokemon.persistence.entities.UserEntity;
import co.edu.uceva.pokemon.persistence.repositories.UserRepository;
import co.edu.uceva.pokemon.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    // Implementación del método para buscar usuario por ID
    @Override
    public UserEntity findUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null); // Retorna null si no encuentra el usuario
    }

    // Implementación del método para actualizar un usuario
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());

            // Solo hash si la contraseña es diferente
            if (!existingUser.getPassword().equals(updatedUser.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            return userRepository.save(existingUser);
        }
        return null;
    }

    // Implementación del método para eliminar un usuario por ID
    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true; // Retorna true si se elimina correctamente
        }
        return false; // Retorna false si no se encuentra el usuario
    }

    @Override
    public UserEntity saveUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }

}
