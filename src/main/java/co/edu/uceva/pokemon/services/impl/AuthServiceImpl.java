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
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final IJWTUtilityService jwtUtilityService;
    private final UserValidations userValidations;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class); // Logger instance

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, IJWTUtilityService jwtUtilityService,
            UserValidations userValidations) {
        this.userRepository = userRepository;
        this.jwtUtilityService = jwtUtilityService;
        this.userValidations = userValidations;
    }

    @Override
    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception {
        try {
            HashMap<String, String> jwt = new HashMap<>();
            Optional<UserEntity> user = userRepository.findByEmail(loginRequest.getEmail());

            if (user.isEmpty()) {
                jwt.put("error", "User not registered!");
                logger.warn("Login attempt with unregistered email: {}", loginRequest.getEmail());
                return jwt;
            }
            if (verifyPassword(loginRequest.getPassword(), user.get().getPassword())) {
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
                logger.info("User {} logged in successfully.", loginRequest.getEmail());
            } else {
                jwt.put("error", "Authentication failed");
                logger.warn("Failed login attempt for user: {}", loginRequest.getEmail());
            }
            return jwt;
        } catch (IllegalArgumentException e) {
            // Solo lanza la excepción sin registrar
            throw new IllegalArgumentException("Error generating JWT", e);
        } catch (Exception e) {
            // Solo lanza la excepción sin registrar
            throw new IllegalArgumentException("Unknown error", e);
        }
    }

    @Override
    public ResponseDTO register(UserEntity user) throws Exception {
        try {
            ResponseDTO response = userValidations.validate(user);
            List<UserEntity> getAllUsers = userRepository.findAll();

            if (response.getNumOfErrors() > 0) {
                logger.warn("User registration failed due to validation errors: {}", response);
                return response;
            }

            for (UserEntity existingUser : getAllUsers) {
                if (existingUser != null && existingUser.getEmail().equals(user.getEmail())) {
                    response.setMessage("User with this email already exists!");
                    logger.warn("Attempt to register existing user: {}", user.getEmail());
                    return response;
                }
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            response.setMessage("User created successfully!");
            logger.info("User {} registered successfully.", user.getEmail());
            return response;
        } catch (Exception e) {
            // Solo lanza la excepción sin registrar
            throw new Exception("Error during registration", e);
        }
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}