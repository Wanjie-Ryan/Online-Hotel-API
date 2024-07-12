package com.hotel.api.repository;

import com.hotel.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// provide the entity name, and the unique identifier type for the entity
public interface UserRepository extends JpaRepository<User, UUID> {


}
