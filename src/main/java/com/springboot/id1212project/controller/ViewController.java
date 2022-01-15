package com.springboot.id1212project.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.id1212project.model.Session;
import com.springboot.id1212project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes(names = {"session"})
public class ViewController {

    private UserService userService;

    //constructor based dependency inject
    public ViewController(UserService userService) {
        super();
        this.userService = userService;
    }

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/home")
    public String showHome(HttpSession session) {
        try{
            Session s = (Session)session.getAttribute("session");
            if (s.isStatus()){
                return "home";
            }else{
                return "index";
            }
        }catch (NullPointerException e){
            return "index";
        }
    }

    @PostMapping("/home")
    public ResponseEntity<String> validateUser(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        System.out.println("email: " + jsonObject.getString("email"));
        System.out.println("password: " + jsonObject.getString("password"));
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        HttpSession session = request.getSession();
        if (userService.loginUser(email, password)) {
            session.setAttribute("session", new Session(email,true));
            System.out.println( session.getAttribute("session"));
            return new ResponseEntity<>("home", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("index", HttpStatus.OK);
        }
    }

    @GetMapping("/myPost")
    public String showPost(HttpSession session) {
        try{
            Session s = (Session)session.getAttribute("session");
            if (s.isStatus()){
                return "myPost";
            }else{
                return "index";
            }
        }catch (NullPointerException e){
            return "index";
        }
    }

    @GetMapping("/myMatch")
    public String showMatch(HttpSession session) {
        try{
            Session s = (Session)session.getAttribute("session");
            if (s.isStatus()){
                return "myMatch";
            }else{
                return "index";
            }
        }catch (NullPointerException e){
            return "index";
        }
    }

    @GetMapping("/myInformation")
    public String showInformation(HttpSession session) {
        try{
            Session s = (Session)session.getAttribute("session");
            if (s.isStatus()){
                return "myInformation";
            }else{
                return "index";
            }
        }catch (NullPointerException e){
            return "index";
        }
    }
}
