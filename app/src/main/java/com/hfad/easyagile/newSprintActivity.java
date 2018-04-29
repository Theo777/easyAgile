package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class newSprintActivity extends AppCompatActivity {

    private sprintDAO sdao;
    private String user;
    private String project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sprint);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                user= null;
                project = null;
            } else {
                user = extras.getString("username");
                project = extras.getString("projectName");
            }
        } else {
            user = (String) savedInstanceState.getSerializable("username");
            project = (String) savedInstanceState.getSerializable("projectName");
        }
        sdao = new sprintDAO(this,user+project);

    }

    public void onSubmit(View view){

        EditText mEdit;
        mEdit   = (EditText)findViewById(R.id.sprintBox);
        String sprint = mEdit.getText().toString();

        sprint = user+project+sprint;

        sdao.insertsprint(sprint);

        Intent intent = new Intent(this,messageBoard.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        extras.putString("projectName",project);
        intent.putExtras(extras);
        startActivity(intent);


    }
}
