package com.example.mock_api;

import java.io.Serializable;

public class Contact implements Serializable {
    private String id ;
    private String name;
    private String numberphone;

    public Contact() {
    }

    public Contact(String id, String name, String numberphone) {
        this.id = id;
        this.name = name;
        this.numberphone = numberphone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }
}
