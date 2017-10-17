package com.phuongnt.studyquiz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.phuongnt.studyquiz.R;
import com.phuongnt.studyquiz.database.DBHelper;
import com.phuongnt.studyquiz.database.DatabaseManager;
import com.phuongnt.studyquiz.model.viewmodel.User;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        DBHelper dbHelper = new DBHelper(this);
//        DatabaseManager.initializeInstance(dbHelper);
        User user = User.getCurrentUser();
        Intent intent = null;
        if(user == null){
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        } else{
            intent = new Intent(SplashActivity.this, MenuActivity.class);
        }
        DatabaseManager.getInstance().closeDatabase();
        startActivity(intent);
    }
}
