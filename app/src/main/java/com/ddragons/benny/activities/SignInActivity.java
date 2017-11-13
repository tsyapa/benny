package com.ddragons.benny.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ddragons.benny.R;
import com.ddragons.benny.utils.Constants;
import com.ddragons.benny.utils.Utils;
import com.ddragons.benny.web.RestClient;
import com.ddragons.benny.web.pojo.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends BaseActivity implements View.OnClickListener{

    Button btnOk, btnCreateAccount;
    EditText etEmail, etPassword;
    private Activity activity=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        btnOk.setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
    }

    private void init(){
        btnOk=(Button) findViewById(R.id.btnOk);
        btnCreateAccount=(Button) findViewById(R.id.btnCreateAccount);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText) findViewById(R.id.etPassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCreateAccount:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btnOk:
                RestClient.getApi().signInConsumer(Constants.PARAM_GRANT_TYPE,etEmail.getText().toString(),etPassword.getText().toString()).enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if(response.code()==Constants.SUCCESS) {
                            prefsManager.saveRegId("bearer " + response.body().getAccessToken());
                            Intent intent = new Intent(activity, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Log.e("error",response.message());
                            Snackbar snackbar = Utils.showSnackBar(btnOk, response.message());
                            snackbar.show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Snackbar snackbar = Utils.showSnackBar(btnOk, "Something went wrong");
                        snackbar.show();
                    }
                });
                break;
        }
    }
}
