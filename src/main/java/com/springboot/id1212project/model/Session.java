package com.springboot.id1212project.model;


import lombok.Data;

@Data
public class Session {
    private String email;
    private boolean status;

    public Session(String email, boolean status) {
        this.email = email;
        this.status = status;
    }
}
