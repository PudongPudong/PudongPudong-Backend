package com.ewha.pudong.repository;

import com.ewha.pudong.domain.Pet;
import com.ewha.pudong.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findPetByUserId(Long id);
}
