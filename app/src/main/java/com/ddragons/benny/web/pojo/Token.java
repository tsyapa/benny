package com.ddragons.benny.web.pojo;

/**
 * Created by Vitaliy Tsyapa on 11/3/2017.
 */

public class Token {

    private String access_token;
    private String token_type;
    private int expires_in;

    public Token(String access_token, String token_type, int expires_in){
        this.access_token=access_token;
        this.token_type=token_type;
        this.expires_in=expires_in;
    }

    public String getAccessToken(){
        return access_token;
    }
}
