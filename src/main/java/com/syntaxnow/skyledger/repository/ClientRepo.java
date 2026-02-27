package com.syntaxnow.skyledger.repository;

import com.syntaxnow.skyledger.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepo extends JpaRepository<Client, UUID> {

}
