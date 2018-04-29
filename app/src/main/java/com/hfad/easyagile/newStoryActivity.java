package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class newStoryActivity extends AppCompatActivity {

    private storyDAO sdao;
    private detailStoryDAO sdao2;
    private String user;
    private String project;
    private String sprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_story);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                user= null;
                project = null;
                sprint = null;
            } else {
                user = extras.getString("username");
                project = extras.getString("projectName");
                sprint = extras.getString("sprintName");
            }
        } else {
            user = (String) savedInstanceState.getSerializable("username");
            project = (String) savedInstanceState.getSerializable("projectName");
            sprint = (String) savedInstanceState.getSerializable("sprintName");
        }
        sdao = new storyDAO(this,user+project+sprint);
        sdao2 = new detailStoryDAO(this,user+project+sprint);



    }


    public void onSubmit(View view){

        EditText mEdit;
        mEdit   = (EditText)findViewById(R.id.storyName);
        String story1 = mEdit.getText().toString();

        story1 = user+project+sprint+story1;

        sdao.insertstory(story1);

        EditText mEdit1;
        mEdit1   = (EditText)findViewById(R.id.Progress);
        String project1 = mEdit1.getText().toString();



        sdao2.insertDetailStory(story1,project1,"not assigned","just started");


        Intent intent = new Intent(this,StorylistActivity.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        extras.putString("projectName",project);
        extras.putString("sprintName",sprint);
        intent.putExtras(extras);

        startActivity(intent);


    }







}
