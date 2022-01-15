package com.springboot.id1212project.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.id1212project.exception.ResourceNotFoundException;
import com.springboot.id1212project.model.Card;
import com.springboot.id1212project.model.Invoice;
import com.springboot.id1212project.model.InvoiceInformation;
import com.springboot.id1212project.model.User;
import com.springboot.id1212project.repository.CardRepository;
import com.springboot.id1212project.repository.InvoiceRepository;
import com.springboot.id1212project.repository.UserRepository;
import com.springboot.id1212project.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private CardRepository cardRepository;
    private UserRepository userRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, CardRepository cardRepository, UserRepository userRepository){
        super();
        this.invoiceRepository = invoiceRepository;
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    public List getInvoiceIdByEmail(String email){
        List id = invoiceRepository.findInvoiceIdByEmail(email);
        return id;
    }
    @Override
    public List<InvoiceInformation> getInvoiceByEmail(String email) {
        List idList = invoiceRepository.findInvoiceIdByEmail(email);
        List<InvoiceInformation> invoices = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++) {
            long id = ((BigInteger)idList.get(i)).longValue();
            invoices.add(invoiceRepository.findInvoiceInformationByEmail(id, email));
        }
        if(!invoices.isEmpty()){
            return invoices;
        }else{
            throw new ResourceNotFoundException("Invoice", "Email", invoices);
        }
    }

    @Override
    public boolean createInvoice(JSONObject jsonObject, User currentUser) {
        //change the status of card

        //date
        long cardId = jsonObject.getLong("id");
        int days = jsonObject.getInteger("days");
        String address = jsonObject.getString("address");

        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        //util.Date
        Date date = new Date();
        long longTime = date.getTime();
        //sql.Date
        java.sql.Date currentDate = new java.sql.Date(longTime);

        Invoice invoice = Invoice
                .builder()
                .address(address)
                .days(days)
                .invoiceDate(currentDate)
                .build();

        Card matchedCard = cardRepository.findById(cardId).get();
        //System.out.println(matchedCard);
        invoice.setCard(matchedCard);
        invoice.addUsers(currentUser);
        invoice.addUsers(userRepository.findById(matchedCard.getUser().getId()).get());
        //System.out.println(invoice);
        invoiceRepository.save(invoice);
        return true;
    }
}
