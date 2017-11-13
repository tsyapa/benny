package com.ddragons.benny.web;

import com.ddragons.benny.utils.Constants;
import com.ddragons.benny.utils.PrefsManager;
import com.ddragons.benny.web.pojo.Registration;
import com.ddragons.benny.web.pojo.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Vitaliy Tsyapa on 10/25/2017.
 */

public interface API {

    @Headers("Content-Type: application/json")
    @POST(Constants.REGISTER_CONSUMER_URL)
    Call<Registration> registerConsumer(@Body Registration registrationBody);

    @FormUrlEncoded
    @POST(Constants.GET_CONSUMER_TOKEN_URL)
    Call<Token> signInConsumer(@Field("grant_type") String grantType, @Field("userName") String userName, @Field("password") String passworrd);

    @GET(Constants.GET_BUSINESSES_URL)
    Call<Token> getBusinesses(@Header("Authorization") String userId);
}
