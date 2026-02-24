package com.syntaxnow.skyledger.model;

import com.syntaxnow.skyledger.enums.Currency;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;
/**
 * Entity representing a Business Profile in the Sky Ledger system.
 *
 * <p>This entity stores complete company information required for
 * accounting, invoicing, document generation, and email communication.
 *
 * <p>The BusinessProfile includes:
 *
 * <ul>
 *   <li>Company details (name, email, phone, address, GST, website)</li>
 *   <li>Branding information (logo URL)</li>
 *   <li>Banking details</li>
 *   <li>Document prefixes (Invoice, Quotation, Credit Note)</li>
 *   <li>Last generated document numbers for sequential numbering</li>
 *   <li>Default currency configuration</li>
 *   <li>SMTP email configuration for sending invoices and quotations</li>
 * </ul>
 *
 * <p>Document Numbering Logic:
 * <ul>
 *   <li>lastInvoiceNumber → Generates numbers like INV-0001</li>
 *   <li>lastQuotationNumber → Generates numbers like QUO-0005</li>
 *   <li>lastCreditNoteNumber → Generates numbers like CN-0003</li>
 * </ul>
 *
 * <p>Technical Details:
 * <ul>
 *   <li>Uses UUID as primary key</li>
 *   <li>Email field must be unique</li>
 *   <li>Currency stored using EnumType.STRING</li>
 *   <li>UUID auto-generated using GenerationType.UUID</li>
 *   <li>Supports Jakarta Validation annotations</li>
 *   <li>Uses Lombok to reduce boilerplate code</li>
 * </ul>
 *
 * <p>This entity is mapped to the "businesses" table and is used by:
 * <ul>
 *   <li>Repository Layer</li>
 *   <li>Service Layer</li>
 *   <li>Controller Layer</li>
 * </ul>
 *
 * <p>Project: Sky Ledger Services – Accounting & Business Management System
 *
 * @author
 *   Korada Jaya Santosh
 * @since 1.0
 */
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
