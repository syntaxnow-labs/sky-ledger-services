package com.syntaxnow.skyledger.service;

import com.syntaxnow.skyledger.model.BusinessProfile;
import com.syntaxnow.skyledger.repository.BusinessRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
