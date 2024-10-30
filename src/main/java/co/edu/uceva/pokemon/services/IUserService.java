package co.edu.uceva.pokemon.services;

import co.edu.uceva.pokemon.persistence.entities.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> findAllUsers();

    UserEntity findUserById(Long id);

    UserEntity updateUser(Long id, UserEntity updatedUser);

    boolean deleteUser(Long id);

    UserEntity saveUser(UserEntity newUser);
}
