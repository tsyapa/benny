package com.ddragons.benny.web.pojo;

/**
 * Created by Vitaliy Tsyapa on 10/26/2017.
 */

public class Registration {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

    public Registration(String firstName, String lastName, String email, String password, String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        this.phoneNumber=phoneNumber;
    }
}
