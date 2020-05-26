package org.example.entity;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String id;
    private String account;
    private String password;
    private String name;
    private Integer age;

    private String[] tags;

    private List<Contact> contacts;
}
