package co.edu.uceva.pokemon.services.impl;

import co.edu.uceva.pokemon.services.models.dtos.LoginDTO;
import co.edu.uceva.pokemon.persistence.entities.UserEntity;
import co.edu.uceva.pokemon.persistence.repositories.UserRepository;
import co.edu.uceva.pokemon.services.IAuthService;
import co.edu.uceva.pokemon.services.IJWTUtilityService;
import co.edu.uceva.pokemon.services.models.dtos.ResponseDTO;
import co.edu.uceva.pokemon.services.models.validations.UserValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IJWTUtilityService jwtUtilityService;

    @Autowired
    private UserValidations userValidations;

    @Override
    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(loginRequest.getEmail());

            if (user.isEmpty()) {
                jwt.put("error", "User not registered!");
                return jwt;
            }
            if (verifyPassword(loginRequest.getPassword(), user.get().getPassword())) {
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
            } else {
                jwt.put("error", "Authentication failed");
            }
            return jwt;
        } catch (IllegalArgumentException e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new Exception("Error generating JWT", e);
        } catch (Exception e) {
            System.err.println("Unknown error: " + e.toString());
            throw new Exception("Unknown error", e);
        }
    }

    @Override
    public ResponseDTO register(UserEntity user) throws Exception {
        try {
            ResponseDTO response = userValidations.validate(user);
            List<UserEntity> getAllUsers = userRepository.findAll();

            if (response.getNumOfErrors() > 0){
                return response;
            }

            for (UserEntity existingUser : getAllUsers) {
                if (existingUser != null && existingUser.getEmail().equals(user.getEmail())) {
                    response.setMessage("User with this email already exists!");
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMessage("User created successfully!");
            return response;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}
