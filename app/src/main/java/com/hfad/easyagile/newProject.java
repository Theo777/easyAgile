package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class newProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
    }

    public void submitProject(View view) {
        Intent intent = new Intent(newProject.this,menuPage.class);
        startActivity(intent);
    }

}
