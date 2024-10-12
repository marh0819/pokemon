package co.edu.uceva.pokemon.services.impl;

import co.edu.uceva.pokemon.persistence.entities.UserEntity;
import co.edu.uceva.pokemon.persistence.repositories.UserRepository;
import co.edu.uceva.pokemon.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> findAllUsers(){
        return userRepository.findAll();
    }
}
