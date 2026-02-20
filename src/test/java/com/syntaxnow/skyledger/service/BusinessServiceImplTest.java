package com.syntaxnow.skyledger.service;

import com.syntaxnow.skyledger.model.BusinessProfile;
import com.syntaxnow.skyledger.repository.BusinessRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceImplTest {

    @Mock
    private BusinessRepo businessRepo;

    @InjectMocks
    private BusinessServiceImpl service;

    @Test
    void getAllBusinessProfiles_shouldReturnAll() {

        BusinessProfile b1 = new BusinessProfile();
        BusinessProfile b2 = new BusinessProfile();

        when(businessRepo.findAll()).thenReturn(List.of(b1, b2));

        List<BusinessProfile> result = service.getAllBusinessProfiles();

        assertEquals(2, result.size());

        verify(businessRepo).findAll();
    }

    @Test
    void getBusiness_found_shouldReturnProfile() {

        UUID id = UUID.randomUUID();

        BusinessProfile profile = new BusinessProfile();

        when(businessRepo.findById(id)).thenReturn(Optional.of(profile));

        BusinessProfile result = service.getBusiness(id);

        assertNotNull(result);

        verify(businessRepo).findById(id);
    }

    @Test
    void createProfile_shouldSaveAndReturn() {

        BusinessProfile profile = new BusinessProfile();

        when(businessRepo.save(profile)).thenReturn(profile);

        BusinessProfile result = service.createProfile(profile);

        assertNotNull(result);

        verify(businessRepo).save(profile);
    }



    @Test
    void updateBusiness_found_shouldSetIdAndSave() {
        UUID id = UUID.randomUUID();
        when(businessRepo.findById(id)).thenReturn(Optional.of(new BusinessProfile()));
        when(businessRepo.save(any(BusinessProfile.class))).thenAnswer(inv -> inv.getArgument(0));

        BusinessProfile input = new BusinessProfile();
        BusinessProfile result = service.updateBusiness(id, input);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(businessRepo).save(input);
    }



    @Test
    void nextNumber_invoice_shouldIncrementAndReturnDocNo() {
        UUID businessId = UUID.randomUUID();

        BusinessProfile b = new BusinessProfile();
        b.setLastInvoiceNumber(1L);
        b.setInvoicePrefix("INV-");

        when(businessRepo.findById(businessId)).thenReturn(Optional.of(b));


        String docNo = service.nextNumber(businessId, "INVOICE");

        assertEquals("INV-2", docNo);
        assertEquals(2, b.getLastInvoiceNumber());

        verify(businessRepo).save(b);
    }
}
