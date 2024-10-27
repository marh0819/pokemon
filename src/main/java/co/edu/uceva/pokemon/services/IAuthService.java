package co.edu.uceva.pokemon.services;

import co.edu.uceva.pokemon.services.models.dtos.LoginDTO;
import co.edu.uceva.pokemon.persistence.entities.UserEntity;
import co.edu.uceva.pokemon.services.models.dtos.ResponseDTO;

import java.util.HashMap;

public interface IAuthService {

    public HashMap<String, String> login(LoginDTO loginRequest) throws Exception;
    public HashMap<String, String> register(UserEntity user) throws Exception;
}
