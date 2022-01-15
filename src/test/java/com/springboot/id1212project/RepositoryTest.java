package com.springboot.id1212project;

import com.springboot.id1212project.exception.ResourceNotFoundException;
import com.springboot.id1212project.model.Card;
import com.springboot.id1212project.model.Invoice;
import com.springboot.id1212project.model.InvoiceInformation;
import com.springboot.id1212project.model.User;
import com.springboot.id1212project.repository.CardRepository;
import com.springboot.id1212project.repository.InvoiceRepository;
import com.springboot.id1212project.repository.UserRepository;
import com.springboot.id1212project.service.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class RepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceService invoiceService;
    @Test
    public void testRepository(){
        List<InvoiceInformation> test = invoiceService.getInvoiceByEmail("zhanbo@kth.se");
        System.out.println("test:" + test.get(0).toString());
        System.out.println("test:" + test.get(1).toString());
        System.out.println("test:" + test.get(2).toString());
    }

//    @Test
//    public void getCardbyUserId(){
//        long id = 2;
//        List<Card> card = cardRepository.findByUserId(id);
//        System.out.println(card);
//    }
//
//    @Test
//    public void getUser(){
//        System.out.println(userRepository.findAll());
//    }
//
//    @Test
//    public void getInvoice(){
//        System.out.println(invoiceRepository.findByCardId(28541));
//    }
//
//    @Test
//    public void saveInvoice(){
//        Invoice invoice = new Invoice();
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//
//        //util.Date
//        Date date = new Date();
//        long longTime = date.getTime();
//        //sql.Date
//        java.sql.Date sDate = new java.sql.Date(longTime);
//        invoice.setInvoiceDate(sDate);
//        invoice.setAddress("campus");
//        invoice.setDays(2);
//
//        invoiceRepository.save(invoice);
//    }
//
//    @Test
//    public void saveUser(){
//        //user
//        User user1 = User
//                .builder()
//                .email("mingli@kth.se")
//                .password("202cb962ac59075b964b07152d234b70")
//                .phone(0)
//                .build();
//
//        userRepository.save(user1);
//
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//        //util.Date
//        Date date = new Date();
//        long longTime = date.getTime();
//        //sql.Date
//        java.sql.Date sDate = new java.sql.Date(longTime);
//        java.sql.Date eDate1 = new java.sql.Date(longTime+200000000);
//
//        //card
//        Card card1 = Card
//                .builder()
//                .cardId(12581)
//                .expiryDate(eDate1)
//                .price("20SEK")
//                .status("post")
//                .type("student")
//                .user(user1)
//                .build();
//        cardRepository.save(card1);
//    }

    //Page
//    @Test
//    public void findAllPagination(){
//        Pageable firstPageWithThreeRecords =
//                PageRequest.of(0, 3);
//
//        List<User> users = userRepository
//                .findAll(firstPageWithThreeRecords)
//                .getContent();
//
//        System.out.println("users:" + users );
//    }
//
//    //Sort
//    @Test
//    public void findAllSorting(){
//        Pageable sortByPrice =
//                PageRequest.of(1,1, Sort.by("price").descending());
//
//
//        List<Card> card =
//                cardRepository.findAll(sortByPrice).getContent();
//        System.out.println("test " + card);
//    }
//
//    @Test
//    public void saveDataExample(){
//        //user
//        User user1 = User
//                .builder()
//                .email("mingli@kth.se")
//                .password("202cb962ac59075b964b07152d234b70")
//                .phone(0)
//                .build();
//
//        User user2 = User
//                .builder()
//                .email("zexun@kth.se")
//                .password("202cb962ac59075b964b07152d234b70")
//                .phone(0)
//                .build();
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//        //util.Date
//        Date date = new Date();
//        long longTime = date.getTime();
//        //sql.Date
//        java.sql.Date sDate = new java.sql.Date(longTime);
//        java.sql.Date eDate1 = new java.sql.Date(longTime+2000);
//        java.sql.Date eDate2 = new java.sql.Date(longTime+3000);
//
//        //card
//        Card card1 = Card
//                .builder()
//                .cardId(38541)
//                .expiryDate(eDate1)
//                .price("19SEK")
//                .status("post")
//                .type("student")
//                .user(user1)
//                .build();
//
//        Card card2 = Card
//                .builder()
//                .cardId(88543)
//                .expiryDate(eDate2)
//                .price("30SEK")
//                .status("post")
//                .type("adult")
//                .user(user2)
//                .build();
//
//        cardRepository.save(card1);
//        cardRepository.save(card2);
//    }
//    @Test
//    public void saveInvoiceWithUserAndCard(){
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
//        //util.Date
//        Date date = new Date();
//        long longTime = date.getTime();
//        //sql.Date
//        java.sql.Date sDate = new java.sql.Date(longTime);
//        java.sql.Date eDate = new java.sql.Date(longTime+1000);

//        //因为这里id是自动生成的所以直接添加可能会破坏1to1的外键约束
//        User user1 = User
//                .builder()
//                .email("zexun@kth.se")
//                .password("202cb962ac59075b964b07152d234b70")
//                .phone(0)
//                .build();
//        User user2 = userRepository.getById(1L);
//        userRepository.save(user1);
//
//        Card newCard = Card.builder()
//                .cardId(48732)
//                .expiryDate(eDate)
//                .price("25SEK")
//                .state("true")
//                .type("adult")
//                .user(user1)
//                .build();
//
//        cardRepository.save(newCard);

        //System.out.println(cardRepository.findByUserId(1));
//        Card card = cardRepository.findByUserId(1).get(0);
//        Invoice invoice = Invoice
//                .builder()
//                .address("campus")
//                .days(4)
//                .invoiceDate(sDate)
//                .build();
//        invoice.setCard(card);
//        System.out.println( userRepository.findById(1L));
//        User user1 = userRepository.findById(1L).get();
//        User user2 = userRepository.findById(2L).get();
//        System.out.println(user1);
//        System.out.println(user2);
//
//
//        invoice.addUsers(user1);
//        invoice.addUsers(user2);
//
//        System.out.println(invoice);
//        invoiceRepository.save(invoice);
//    }
}
