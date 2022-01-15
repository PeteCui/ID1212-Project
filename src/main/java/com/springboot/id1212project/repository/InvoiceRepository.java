package com.springboot.id1212project.repository;

import com.springboot.id1212project.model.Invoice;
import com.springboot.id1212project.model.InvoiceInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository <Invoice, Long> {

    @Query(
            value = "SELECT * FROM invoice WHERE card_id = ?1",
            nativeQuery = true
    )
    List<Invoice> findByCardId(long card_id);

    @Query(
            nativeQuery = true,
            value = "SELECT invoice.invoice_id as invoiceId, invoice.address as address , invoice.days as days, invoice.invoice_date as invoiceDate, invoice.card_id as cardId, users.email as email, users.phone as phone FROM users,invoice_user_map,invoice WHERE users.id = invoice_user_map.user_id AND invoice_user_map.invoice_id = invoice.invoice_id AND invoice.invoice_id = ?1 AND users.email <> ?2 "
    )
    InvoiceInformation findInvoiceInformationByEmail(long id, String email);

    @Query(
            value = "SELECT invoice.invoice_id FROM users,invoice_user_map,invoice WHERE invoice_user_map.invoice_id = invoice.invoice_id AND invoice_user_map.user_id = users.id AND users.email = ?1",
            nativeQuery = true
    )
    List findInvoiceIdByEmail(String email);
}
