package co.edu.uceva.pokemon.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.uceva.pokemon.persistence.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);
}
