package com.syntaxnow.skyledger.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.syntaxnow.skyledger.enums.CustomerType;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "Customer type is required")
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    private String salutation;
    private String firstName;
    private String lastName;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    private String phone;
    private String mobile;
    private String gstNumber;
    private String gstTreatment;
    private String pan;
    private String placeOfSupply;

    @NotBlank(message = "Currency is required")
    private String currency;

    private String paymentTerms;

    @NotBlank(message = "Billing address is required")
    private String billingAddress;
    private String billingStreet;
    private String billingCity;
    private String billingState;
    private String billingZip;
    private String billingCountry;
    private String shippingAddress;
    private String shippingStreet;
    private String shippingCity;
    private String shippingState;
    private String shippingZip;
    private String shippingCountry;
}