package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class newProject extends AppCompatActivity {

    private projectDAO pdao;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                user= null;
            } else {
                user = extras.getString("username");
            }
        } else {
            user = (String) savedInstanceState.getSerializable("username");
        }
        pdao = new projectDAO(this,user);
    }

    public void submitProject(View view) {

        EditText mEdit;
        mEdit   = (EditText)findViewById(R.id.editText4);
        String project = mEdit.getText().toString();

        project = user+project;
        Log.i("newProject","!!!!!!!!!!!!!1 project name"+project);
        pdao.insertproject(project);


        Intent intent = new Intent(newProject.this,menuPage.class);
        intent.putExtra("username",user);
        startActivity(intent);
    }

}
