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

    @Override
    public UserEntity findUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());

            if (!existingUser.getPassword().equals(updatedUser.getPassword())) {
                existingUser.setPassword(updatedUser.getPassword());
            }

            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public UserEntity saveUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }
}
