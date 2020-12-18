package com.example.apiretro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    EditText emailEt,passwordEt;
    Button loginBtn;
    TextView signUpTv;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         try {
            emailEt = findViewById(R.id.emailEt);
            passwordEt = findViewById(R.id.passwordEt);
            loginBtn = findViewById(R.id.loginBtn);
            signUpTv = findViewById(R.id.signUpTv);
            loginButtonClick();
            signUpTextViewClick();

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void loginButtonClick(){
        try {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkValidation()) {
                        String email = emailEt.getText().toString();
                        String password = passwordEt.getText().toString();
                        String authToken = createAuthToken(email, password);
                        checkLoginDetails(authToken);
                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void signUpTextViewClick(){
        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,SignUp.class));
            }
        });
    }
    private String createAuthToken(String email, String password) {
        byte[] data=new byte[0];
        try {
            data=(email + ":" + password).getBytes("UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return "Basic " + Base64.encodeToString(data,Base64.NO_WRAP);
    }
    private void checkLoginDetails(String authToken){
        try{
        Retrofit retrofit=APIClient.getClient();
        final APIInterface api=retrofit.create(APIInterface.class);

        Call<String> call=api.checkLogin(authToken);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(response.body().matches("success")){
                        Toast.makeText(Login.this, "Successful Login ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this, "response is un successful", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                 Log.e("TAG",t.toString());
                 t.printStackTrace();
            }
        });
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkValidation() {
        email = emailEt.getText().toString();
        password = passwordEt.getText().toString();

        if (emailEt.getText().toString().trim().equals("")) {
             Toast.makeText(this, "email Cannot be left blank", Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordEt.getText().toString().trim().equals("")) {
             Toast.makeText(this, "password Cannot be left blank", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}