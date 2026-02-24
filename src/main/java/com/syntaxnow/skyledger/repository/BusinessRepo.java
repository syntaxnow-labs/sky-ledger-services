package com.syntaxnow.skyledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.syntaxnow.skyledger.model.BusinessProfile;

import java.util.UUID;

public interface BusinessRepo extends JpaRepository<BusinessProfile, UUID> {
}
