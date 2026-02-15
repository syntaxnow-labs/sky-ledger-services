package com.syntaxnow.skyledger.model;

import com.syntaxnow.skyledger.enums.Currency;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "businesses")
public class BusinessProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    private String phone;
    private String address;
    private String gstNumber;
    private String logoUrl;
    private String website;

    private String bankDetails;

    private String invoicePrefix;
    private String quotationPrefix;
    private String creditNotePrefix;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    private Long lastInvoiceNumber = 0L;
    private Long lastQuotationNumber = 0L;
    private Long lastCreditNoteNumber = 0L;

    private String smtpHost;
    private Integer smtpPort;
    private String smtpUser;
    private String smtpPass;
    private Boolean smtpSecure;
}
