package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.database.UserDB;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginRequest;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginResponse;
import com.phuongnt.studyquiz.model.viewmodel.User;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.utils.MyProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUsername = null;
    private EditText edtPassword = null;
    private IAPIHelper iapiHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        iapiHelper = APIManager.getAPIManager().create(IAPIHelper.class);
    }

    public void onLoginButtonSelected(View v){
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        LoginRequest loginRequest = new LoginRequest(username, password);
        try{
            Call<CommonResponse<LoginResponse>> call  = iapiHelper.login(loginRequest);
            MyProgressBar.show(this);

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
        } catch(Exception e){
            MyProgressBar.dismiss();
            onError("Something was wrong");
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
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void onError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        MyProgressBar.dismiss();
    }

    boolean doubleBack = false;

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            return;
        }

        this.doubleBack = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBack = false;
            }
        }, 2000);
    }


}
