package com.ddragons.benny.models;

/**
 * Created by Vitaliy Tsyapa on 10/25/2017.
 */

public class Consumer {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    public Consumer(String firstName, String lastName, String email, String password, String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
    }
}
