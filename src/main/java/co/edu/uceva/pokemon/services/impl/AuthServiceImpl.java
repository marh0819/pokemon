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
                // Incluimos el firstName en la respuesta
                jwt.put("jwt", jwtUtilityService.generateJWT(user.get().getId()));
                jwt.put("firstName", user.get().getFirstName()); // Añadimos el nombre del usuario
                jwt.put("lastName", user.get().getLastName()); // Añadimos el apellido del usuario
                jwt.put("email", user.get().getEmail()); // Añadimos el email del usuario
                jwt.put("id", user.get().getId().toString()); // Añadimos el id del usuario
                logger.info("User {} logged in successfully.", loginRequest.getEmail());
            } else {
                jwt.put("error", "Authentication failed");
                logger.warn("Failed login attempt for user: {}", loginRequest.getEmail());
            }
            return jwt;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error generating JWT", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unknown error", e);
        }
    }

    @Override
    public HashMap<String, String> register(UserEntity user) throws Exception {
        HashMap<String, String> response = new HashMap<>();
        try {
            // Validar el usuario antes de registrarlo
            ResponseDTO validationResponse = userValidations.validate(user);
            if (validationResponse.getNumOfErrors() > 0) {
                response.put("error", "User validation failed: " + validationResponse.getMessage());
                return response;
            }

            // Verificar si el usuario ya existe
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                response.put("error", "User with this email already exists!");
                logger.warn("Attempt to register existing user: {}", user.getEmail());
                return response;
            }

            // Codificar la contraseña y guardar el usuario
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);

            // Agregar información del usuario y el token en la respuesta
            response.put("id", user.getId().toString());
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("email", user.getEmail());

            logger.info("User {} registered successfully with JWT.", user.getEmail());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error during registration", e);
        }
        return response;
    }

    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(enteredPassword, storedPassword);
    }
}