package com.phuongnt.studyquiz.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.model.apimodel.CommonResponse;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginRequest;
import com.phuongnt.studyquiz.model.apimodel.loginservice.LoginResponse;
import com.phuongnt.studyquiz.service.APIManager;
import com.phuongnt.studyquiz.service.IAPIHelper;
import com.phuongnt.studyquiz.service.MyProgressBar;

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
        System.out.println(edtUsername.getText().toString() + edtPassword.getText().toString());
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        LoginRequest loginRequest = new LoginRequest(username, password);
        Call<CommonResponse<LoginResponse>> call  = iapiHelper.login(loginRequest);
        MyProgressBar.show(this);

        call.enqueue(new Callback<CommonResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<CommonResponse<LoginResponse>> call, Response<CommonResponse<LoginResponse>> response) {
                int code = response.code();
                CommonResponse<LoginResponse> commonResponse = response.body();
                LoginResponse loginResponse = commonResponse.getValue();
                if(commonResponse.isSuccess()){
                    onSuccess();
                } else{
                    onError();
                }
            }
            @Override
            public void onFailure(Call<CommonResponse<LoginResponse>> call, Throwable t) {
                onError();
            }
        });
    }

    private void onSuccess(){
        MyProgressBar.dismiss();
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void onError(){
        Toast.makeText(this, "Something was wrong", Toast.LENGTH_SHORT).show();
        MyProgressBar.dismiss();
    }
}
