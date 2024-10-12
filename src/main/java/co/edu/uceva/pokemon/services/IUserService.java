package co.edu.uceva.pokemon.services;

import co.edu.uceva.pokemon.persistence.entities.UserEntity;

import java.util.List;

public interface IUserService {

    public List<UserEntity> findAllUsers();
}
