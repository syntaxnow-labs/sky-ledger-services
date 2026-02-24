package com.syntaxnow.skyledger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.syntaxnow.skyledger.model.BusinessProfile;

import java.util.UUID;

/**
 * Repository interface for managing {@link com.syntaxnow.skyledger.model.BusinessProfile}
 * entities.
 *
 * <p>This interface extends {@link org.springframework.data.jpa.repository.JpaRepository}
 * and provides built-in CRUD operations for the BusinessProfile entity.
 *
 * <p>Primary Responsibilities:
 * <ul>
 *   <li>Persisting BusinessProfile entities to the database</li>
 *   <li>Retrieving business profiles by UUID</li>
 *   <li>Fetching all business profiles</li>
 *   <li>Updating existing business records</li>
 *   <li>Deleting business records</li>
 * </ul>
 *
 * <p>The primary key type for BusinessProfile is {@link java.util.UUID}.
 *
 * <p>Spring Data JPA automatically provides implementations for
 * common database operations such as:
 * <ul>
 *   <li><code>findAll()</code></li>
 *   <li><code>findById(UUID id)</code></li>
 *   <li><code>save(BusinessProfile entity)</code></li>
 *   <li><code>deleteById(UUID id)</code></li>
 * </ul>
 *
 * <p>Additional custom query methods can be defined here if required,
 * following Spring Data JPA method naming conventions.
 *
 * <p><strong>Architecture Role:</strong><br>
 * Acts as the Data Access Layer between:
 * Service Layer → Repository → Database
 *
 * <p>Project: Sky Ledger Services – Accounting & Business Management System
 *
 * @author
 *   Korada Jaya Santosh
 * @since 1.0
 */

public interface BusinessRepo extends JpaRepository<BusinessProfile, UUID> {
}
