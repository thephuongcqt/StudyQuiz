package com.phuongnt.studyquiz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.phuongnt.studyquiz.R;

public class SignUpActivity extends AppCompatActivity {
    private EditText username,pass1,pass2,email,name;
    private String Susername,Spass1,Spass2,Semail,Sname;
    private Button SignUp;
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
    }
    public void register(){
        checkTrim();
        if(!validate()){
            Toast.makeText(this,"SIGNUP FAILED",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"DONE ",Toast.LENGTH_SHORT).show();
        }
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
