package edu.knoldus.user.user.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User  {

    int id;
    String name;
/*
    public User(int inputId, String inputName) {
        id = inputId;
        name = inputName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }*/
}
