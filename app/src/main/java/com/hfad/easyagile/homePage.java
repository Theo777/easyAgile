package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class homePage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }

    public void onsubmitButton(View view){



    Intent intent = new Intent(this,menuPage.class);

    startActivity(intent);

    }

    public void onnewUser(View view){

        Intent intent = new Intent(this,newUserActivity.class);

        startActivity(intent);

    }

}
