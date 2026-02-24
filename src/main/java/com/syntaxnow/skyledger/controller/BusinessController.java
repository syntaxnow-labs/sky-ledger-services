package com.syntaxnow.skyledger.controller;

import com.syntaxnow.skyledger.model.BusinessProfile;
import com.syntaxnow.skyledger.service.BusinessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**
 * REST controller responsible for managing Business Profile operations
 * in the Sky Ledger application.
 *
 * <p>This controller exposes RESTful endpoints to:
 * <ul>
 *   <li>Create a new business profile</li>
 *   <li>Retrieve all business profiles</li>
 *   <li>Retrieve a business profile by UUID</li>
 *   <li>Update an existing business profile</li>
 *   <li>Generate the next sequential document number for a given business and type</li>
 * </ul>
 *
 * <p><strong>Base URL:</strong> <code>/business</code>
 *
 * <p>This controller does not contain business logic. All processing is delegated
 * to the {@link com.syntaxnow.skyledger.service.BusinessService} layer to maintain
 * separation of concerns and follow RESTful API best practices.
 *
 * @author
 *   Korada Jaya Santosh
 * @since 1.0
 */

@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping
    public List<BusinessProfile> getAllProfiles() {
        return businessService.getAllBusinessProfiles();
    }


    @GetMapping("/{id}")
    public BusinessProfile getBusiness(@PathVariable UUID id) {
        return businessService.getBusiness(id);
    }


    @PostMapping
    public BusinessProfile createProfile(@RequestBody BusinessProfile profile) {
        return businessService.createProfile(profile);
    }


    @PutMapping("/{id}")
    public BusinessProfile updateBusiness(@PathVariable UUID id, @RequestBody BusinessProfile profile) {
        return businessService.updateBusiness(id, profile);
    }

    @GetMapping("/{businessId}/next-number/{type}")
    public String getNextNumber( @PathVariable UUID businessId, @PathVariable String type) {
        return businessService.nextNumber(businessId, type);
    }

}
