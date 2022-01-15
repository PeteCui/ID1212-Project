package com.springboot.id1212project.service;

import com.alibaba.fastjson.JSONObject;
import com.springboot.id1212project.model.Card;
import com.springboot.id1212project.model.InvoiceInformation;
import com.springboot.id1212project.model.User;

import java.util.List;

public interface InvoiceService {

    List getInvoiceIdByEmail(String email);
    List<InvoiceInformation> getInvoiceByEmail(String email);
    boolean createInvoice(JSONObject jsonObject, User currentUser);
}
