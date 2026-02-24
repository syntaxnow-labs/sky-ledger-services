package com.syntaxnow.skyledger.service;

import com.syntaxnow.skyledger.model.BusinessProfile;
import com.syntaxnow.skyledger.repository.BusinessRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * Service implementation class for managing Business Profile operations.
 *
 * <p>This class provides the concrete implementation of the
 * {@link BusinessService} interface and contains the core business logic
 * related to {@link com.syntaxnow.skyledger.model.BusinessProfile}.
 *
 * <p>Responsibilities include:
 *
 * <ul>
 *   <li>Retrieving all business profiles</li>
 *   <li>Fetching a specific business profile by UUID</li>
 *   <li>Creating a new business profile with email uniqueness validation</li>
 *   <li>Updating an existing business profile</li>
 *   <li>Generating sequential document numbers
 *       (Invoice, Quotation, Credit Note)</li>
 * </ul>
 *
 * <p><strong>Email Validation:</strong><br>
 * Ensures that no two business profiles share the same email address.
 * If a duplicate email is detected, a
 * {@link org.springframework.web.server.ResponseStatusException}
 * with HTTP 409 (CONFLICT) is thrown.
 *
 * <p><strong>Document Number Generation Logic:</strong>
 * <ul>
 *   <li>INVOICE → Uses <code>lastInvoiceNumber</code></li>
 *   <li>QUOTATION → Uses <code>lastQuotationNumber</code></li>
 *   <li>CREDIT_NOTE → Uses <code>lastCreditNoteNumber</code></li>
 * </ul>
 *
 * <p>Each call to <code>nextNumber()</code>:
 * <ul>
 *   <li>Increments the corresponding last number field</li>
 *   <li>Applies a custom prefix if configured</li>
 *   <li>Falls back to a default prefix (INV-, QT-, CN-)</li>
 *   <li>Persists the updated counter in the database</li>
 * </ul>
 *
 * <p><strong>Transactional Behavior:</strong><br>
 * Methods that modify data are annotated with
 * {@link org.springframework.transaction.annotation.Transactional}
 * to ensure data consistency.
 *
 * <p><strong>Architecture Role:</strong><br>
 * Acts as the business layer between:
 * Controller → Service → Repository → Database
 *
 * <p>This class is annotated with:
 * <ul>
 *   <li>{@link org.springframework.stereotype.Service}</li>
 *   <li>{@link lombok.RequiredArgsConstructor}</li>
 * </ul>
 *
 * <p>Project: Sky Ledger Services – Accounting & Business Management System
 *
 * @author
 *   Korada Jaya Santosh
 * @since 1.0
 */

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepo businessRepo;

    @Override
    public List<BusinessProfile> getAllBusinessProfiles() {
        return businessRepo.findAll();
    }

    @Override
    public BusinessProfile getBusiness(UUID id) {
        return businessRepo.findById(id).orElse(null);
    }

    @Override
    public BusinessProfile createProfile(BusinessProfile profile) {

        if (businessRepo.findAll().stream().anyMatch(businessProfile -> businessProfile.getEmail().equalsIgnoreCase(profile.getEmail()))){
            throw new ResponseStatusException( HttpStatus.CONFLICT,
               "Email already exists"
            );

        }
        return businessRepo.save(profile);
    }

    @Override
    @Transactional
    public BusinessProfile updateBusiness(UUID id, BusinessProfile profile) {

        BusinessProfile existing = businessRepo.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        profile.setId(id);

        return businessRepo.save(profile);
    }

    @Override
    @Transactional
    public String nextNumber(UUID businessId, String type) {

        BusinessProfile b = businessRepo.findById(businessId).orElse(null);

        if (b == null) {
            return "BUSINESS_NOT_FOUND";
        }

        String docNo;

        switch (type.toUpperCase()) {

            case "INVOICE" -> {
                long next = b.getLastInvoiceNumber() + 1;
                b.setLastInvoiceNumber(next);
                docNo = prefix(b.getInvoicePrefix(), "INV-") + next;
            }

            case "QUOTATION" -> {
                long next = b.getLastQuotationNumber() + 1;
                b.setLastQuotationNumber(next);
                docNo = prefix(b.getQuotationPrefix(), "QT-") + next;
            }

            case "CREDIT_NOTE" -> {
                long next = b.getLastCreditNoteNumber() + 1;
                b.setLastCreditNoteNumber(next);
                docNo = prefix(b.getCreditNotePrefix(), "CN-") + next;
            }

            default -> {
                return "INVALID_TYPE";
            }
        }
        businessRepo.save(b);
        return docNo;
    }

    private String prefix(String value, String def) {
        return (value == null || value.isBlank()) ? def : value;
    }
}
