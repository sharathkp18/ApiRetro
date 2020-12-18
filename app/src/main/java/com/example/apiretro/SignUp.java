package com.example.apiretro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    EditText employeeNameEt,emailEt,mobileNoEt,passwordEt,conPasswordEt;
    Button signUpBtn;
    TextView loginTv;
    RadioGroup genderRg;
    RadioButton radioBtn;
    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        try{
            apiInterface = APIClient.getClient().create(APIInterface.class);

            employeeNameEt=findViewById(R.id.employeeNameEt);
            emailEt=findViewById(R.id.emailEt);
            mobileNoEt=findViewById(R.id.mobileNoEt);
            passwordEt=findViewById(R.id.passwordEt);
            conPasswordEt=findViewById(R.id.confPasswordEt);
            signUpBtn=findViewById(R.id.signUpBtn);
            loginTv=findViewById(R.id.logInTv);
            genderRg=findViewById(R.id.genderRg);
            signUpButtonClick();
            loginTextViewClick();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void signUpButtonClick(){
        try {
            signUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (employeeNameEt.getText().toString().trim().isEmpty()
                            || emailEt.getText().toString().trim().isEmpty()
                            || mobileNoEt.getText().toString().trim().isEmpty()
                            || genderRg.getCheckedRadioButtonId() == -1
                            || passwordEt.getText().toString().trim().isEmpty()
                            || conPasswordEt.getText().toString().trim().isEmpty()) {
                        Toast.makeText(SignUp.this, "please fill all the fields", Toast.LENGTH_SHORT).show();
                    } else if (passwordEt.getText().toString().trim().length() < 4) {
                        passwordEt.setError("password length should be more than 4");
                    } else if (!passwordEt.getText().toString().equals(conPasswordEt.getText().toString())) {
                        conPasswordEt.setError("password didn't match");
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public String getGender(int radioButtonId){
        radioBtn=findViewById(radioButtonId);
        return radioBtn.getText().toString();
    }
    public void loginTextViewClick(){
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });
    }
}