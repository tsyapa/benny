package com.ddragons.benny.activities;

import android.app.Activity;
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
import com.ddragons.benny.web.pojo.Registration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    Button btnOk;
    EditText etFirstName, etLastName, etEmail, etPassword, etPhoneNumber;
    private Activity activity=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        btnOk.setOnClickListener(this);
    }

    private void init(){
        btnOk=(Button) findViewById(R.id.button);
        etFirstName=(EditText) findViewById(R.id.etFirstName);
        etLastName=(EditText) findViewById(R.id.etLastName);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPassword=(EditText) findViewById(R.id.etPassword);
        etPhoneNumber=(EditText) findViewById(R.id.etPhoneNumber);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Registration registrationBody=new Registration(etFirstName.getText().toString(),etLastName.getText().toString(),etEmail.getText().toString(),etPassword.getText().toString(),etPhoneNumber.getText().toString());
                RestClient.getApi().registerConsumer(registrationBody).enqueue(new Callback<Registration>() {
                    @Override
                    public void onResponse(Call<Registration> call, Response<Registration> response) {
                        if(response.code()== Constants.SUCCESS) {
                            showDialog(activity,"","Registration successfully completed");
                        }
                        else {
                            Log.e("error",response.message());
                            Snackbar snackbar = Utils.showSnackBar(btnOk, response.message());
                            snackbar.show();
                        }

                    }
                    @Override
                    public void onFailure(Call<Registration> call, Throwable t) {
                        Snackbar snackbar = Utils.showSnackBar(btnOk, "Something went wrong");
                        snackbar.show();
                    }
                });
                break;
        }
    }

}
