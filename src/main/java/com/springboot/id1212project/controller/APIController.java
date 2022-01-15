package com.springboot.id1212project.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.id1212project.exception.ResourceNotFoundException;
import com.springboot.id1212project.model.Card;
import com.springboot.id1212project.model.InvoiceInformation;
import com.springboot.id1212project.model.Session;
import com.springboot.id1212project.model.User;
import com.springboot.id1212project.service.CardService;
import com.springboot.id1212project.service.InvoiceService;
import com.springboot.id1212project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//This is a convenient annotation that combines @Controller and @ResponseBody
@RestController
@RequestMapping("/api")
@SessionAttributes(names = {"session"})
public class APIController {

    private UserService userService;
    private CardService cardService;
    private InvoiceService invoiceService;

    //constructor based dependency inject
    public APIController(UserService userService, CardService cardService, InvoiceService invoiceService) {
        super();
        this.userService = userService;
        this.cardService = cardService;
        this.invoiceService = invoiceService;
    }

    //create user REST API
    //@RequestBody annotation allows us to retrieve the request's body and automatically convert it to Java Object
    @PostMapping("user")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        //pass the body and status
        System.out.println(user.toString());
        //return null;
        //return new ResponseEntity<>("no", HttpStatus.OK);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    //get all users REST API
    @GetMapping("user")
    public ResponseEntity<User> getAllUsers(){
        return new ResponseEntity(userService.getAllUser(),HttpStatus.OK);
    }

    //get current users REST API
    @GetMapping("user/me")
    public ResponseEntity<User> getMyInformation(HttpSession session){
        Session s = (Session)session.getAttribute("session");
        System.out.println( session.getAttribute("session"));
        String email = s.getEmail();
//        String email = "zhanbo@kth.se";
        return new ResponseEntity(userService.getUserByEmail(email),HttpStatus.OK);
    }

    //get user by id REST API
    //http://localhost:8080/api/user/1
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    //update user REST API
    //http://localhost:8080/api/user/1
    @PutMapping("user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id,
                                           @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(user, id),HttpStatus.OK);
    }

    //Delete user REST API
    //http://localhost:8080/api/user/1
    @DeleteMapping("user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    //http://localhost:8080/api/card
    @GetMapping("card")
    public ResponseEntity<Card> getAllCards(){
        return new ResponseEntity(cardService.getCardWithState("post"),HttpStatus.OK);
    }

    @GetMapping("card/me")
    public ResponseEntity<Card> getMyCard(HttpSession session){
        Session s = (Session)session.getAttribute("session");
        String email = s.getEmail();
//        String email = "zhanbo@kth.se";
        long userId = userService.getUserByEmail(email).getId();
        return new ResponseEntity(cardService.getCardByUserId(userId).get(0),HttpStatus.OK);
    }

    @PutMapping("card")
    public String updateCardState(HttpSession session,
                                  @RequestBody JSONObject jsonObject){
        Session s = (Session)session.getAttribute("session");
        String email = s.getEmail();
        String operation = jsonObject.getString("update");
        User currentUser = userService.getUserByEmail(email);
        long userId = currentUser.getId();

        if (operation.equals("post")) {
            Card currentCard = cardService.updateCardState(userId, operation);
            if (operation.equals(currentCard.getStatus())) {
                return "postOk";
            } else {
                return "something wrong!";
            }
        }else if(operation.equals("withdraw")){
            Card currentCard = cardService.updateCardState(userId, operation);
            if (operation.equals(currentCard.getStatus())) {
                return "withdrawOk";
            } else {
                return "something wrong!";
            }
        }else if(operation.equals("match")) {
            cardService.updateCardStateById(jsonObject.getLong("id"), operation);
            if (invoiceService.createInvoice(jsonObject, currentUser)) {
                return "matchOk";
            } else {
                return "something wrong!";
            }
        }
        return "something wrong!";

    }

    @PutMapping("card/price")
    public String updatePrice(HttpSession session,
                              @RequestBody JSONObject jsonObject){
        Session s = (Session)session.getAttribute("session");
        String email = s.getEmail();
        String operation = jsonObject.getString("update");
        if(operation.equals("changePrice")) {
            User currentUser = userService.getUserByEmail(email);
            long userId = currentUser.getId();
            System.out.println("userID: " + userId);
            Card card = cardService.updateCardPriceByEmail(userId, jsonObject.getString("price"));
            if(card.getPrice().equals(jsonObject.getString("price"))){
                return "changeOk";
            }
        }
        return "something wrong!";

    }

    @GetMapping("invoice/me")
    public ResponseEntity<InvoiceInformation> getMyInvoiceInformation(HttpSession session){
        Session s = (Session)session.getAttribute("session");
        String email = s.getEmail();
//        String email = "zhanbo@kth.se";
        return new ResponseEntity(invoiceService.getInvoiceByEmail(email),HttpStatus.OK);
    }

}
