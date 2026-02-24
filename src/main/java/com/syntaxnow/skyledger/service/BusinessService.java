package com.syntaxnow.skyledger.service;

import com.syntaxnow.skyledger.model.BusinessProfile;
import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing Business Profile operations.
 *
 * <p>This interface defines the core business functionalities related to
 * {@link com.syntaxnow.skyledger.model.BusinessProfile}
 * such as:
 *
 * <ul>
 *   <li>Retrieving a business profile by its unique identifier</li>
 *   <li>Creating a new business profile</li>
 *   <li>Updating an existing business profile</li>
 *   <li>Generating the next running number for a specific business and type
 *       (for example: invoice number, order number, etc.)</li>
 * </ul>
 *
 * <p>The actual implementation of these operations should be provided in a
 * corresponding service implementation class (e.g., <code>BusinessServiceImpl</code>),
 * typically annotated with Spring’s <code>@Service</code> and
 * <code>@Transactional</code> where required.
 *
 * <p>This interface is part of the business layer in a Spring Boot application
 * and acts as a contract between the controller layer and the data access layer.
 *
 * @author
 *   Korada Jaya Santosh
 * @since 1.0
 */

public interface BusinessService {

    List<BusinessProfile> getAllBusinessProfiles();

    BusinessProfile getBusiness(UUID id);

    BusinessProfile createProfile(BusinessProfile profile);

    BusinessProfile updateBusiness(UUID id, BusinessProfile profile);

    String nextNumber(UUID businessId, String type);
}


