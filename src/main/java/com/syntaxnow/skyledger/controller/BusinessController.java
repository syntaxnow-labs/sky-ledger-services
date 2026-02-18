package com.syntaxnow.skyledger.controller;

import com.syntaxnow.skyledger.model.BusinessProfile;
import com.syntaxnow.skyledger.service.BusinessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public String getNextNumber(@PathVariable UUID businessId, @PathVariable String type) {
        return businessService.nextNumber(businessId, type);
    }
}
