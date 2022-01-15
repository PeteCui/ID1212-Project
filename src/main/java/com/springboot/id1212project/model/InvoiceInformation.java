package com.springboot.id1212project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


public interface InvoiceInformation {

    long getInvoiceId();
    String getAddress();
    int getDays();
    Date getInvoiceDate();
    long getCardId();

    String getEmail();
    int getPhone();

}
