package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.database.UserDB;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginResponse;
import com.phuongnt.studyquiz.model.apimodel.signupservice.SignUpRequest;
import com.phuongnt.studyquiz.model.viewmodel.User;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.utils.MyProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpActivity extends AppCompatActivity {
    private EditText username,pass1,pass2,email,name;
    private String Susername,Spass1,Spass2,Semail,Sname;
    private Button SignUp;
    private IAPIHelper iapiHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = (EditText)findViewById(R.id.username);
        pass1 = (EditText)findViewById(R.id.password);
        pass2 = (EditText)findViewById(R.id.confirmPassword);
        email = (EditText)findViewById(R.id.email);
        name =  (EditText)findViewById(R.id.name);
        SignUp = (Button)findViewById(R.id.btnSignUp);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
    }
    public void register(){
        checkTrim();
        if(!validate()){
            Toast.makeText(this,"SIGNUP FAILED",Toast.LENGTH_SHORT).show();
        }else{
            String UsernameSS = username.getText().toString().trim();
            String PasswordSS = pass1.getText().toString().trim();
            String EmailSS = email.getText().toString().trim();
            String NameSS = name.getText().toString().trim();

            SignUpRequest signUpRequest = new SignUpRequest(UsernameSS,PasswordSS,EmailSS,NameSS);
            try {
                MyProgressBar.show(this);
                Call<CommonResponse<LoginResponse>> call  = iapiHelper.singUp(signUpRequest);
                call.enqueue(new Callback<CommonResponse<LoginResponse>>() {
                    @Override
                    public void onResponse(Call<CommonResponse<LoginResponse>> call, Response<CommonResponse<LoginResponse>> response) {
                        if(response.isSuccessful()){
                            CommonResponse<LoginResponse> commonResponse = response.body();
                            LoginResponse loginResponse = commonResponse.getValue();
                            if(commonResponse.isSuccess()){
                                onSuccess(loginResponse);
                            } else{
                                onError(commonResponse.getError());
                            }
                        } else{
                            onError("Connection error");
                        }
                    }

                    @Override
                    public void onFailure(Call<CommonResponse<LoginResponse>> call, Throwable t) {
                        onError("Connection error");
                    }
                });
            }catch(Exception e){
                MyProgressBar.dismiss();
                onError("Something was wrong");
            }
        }
    }
    private void onSuccess(LoginResponse response){
        User user = new User();
        user.setUserId(response.getUserId());
        user.setUsername(response.getUsername());
        user.setPassword(response.getPassword());
        user.setEmail(response.getEmail());
        user.setName(response.getName());
        user.setRole(response.getRole());

        boolean success = new UserDB().insert(user);
        if(!success){
            onError("Store database fail");
            return;
        }
        MyProgressBar.dismiss();
        Intent intent = new Intent(SignUpActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void onError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        MyProgressBar.dismiss();
    }
    public boolean validate(){
        boolean valid = true;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(Susername.isEmpty() || Susername.length()>32){
            username.setError("Please Enter valid Username");
            valid =false;
        }
        if(Spass1.isEmpty() || Spass1.length()>32){
            pass1.setError("Please input password");
            valid =false;
        }
        if(Spass2.isEmpty() ){
            pass2.setError("Please input confirm password");
            valid =false;
        }
        if (!Semail.matches(emailPattern))
        {
            email.setError("Invalid Email");
            valid=false;
        }


        if(!Spass1.equalsIgnoreCase(Spass2)){
            pass2.setError("Password confirm is not correct");
            valid=false;
        }
        if(Sname.isEmpty() ){
            name.setError("Please input confirm password");
            valid =false;
        }
        return valid;
    }
    public void checkTrim(){
        Susername = username.getText().toString().trim();
        Spass1 = pass1.getText().toString().trim();
        Spass2 = pass2.getText().toString().trim();
        Semail = email.getText().toString().trim();
        Sname  = name.getText().toString().trim();
    }
}
