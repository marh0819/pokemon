package co.edu.uceva.pokemon.services;

import co.edu.uceva.pokemon.persistence.entities.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> findAllUsers();

    UserEntity findUserById(Long id);

    UserEntity updateUser(Long id, UserEntity updatedUser);

    boolean deleteUser(Long id);

    // Método para guardar un nuevo usuario
    UserEntity saveUser(UserEntity newUser);
}

