package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class detailStoryView extends AppCompatActivity {

    private String user;
    private String project;
    private String sprint;
    private String story;
    private detailStoryDAO dsDAO;
    private ArrayList stories;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story_view);
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 1");
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                user= null;
                project = null;
                sprint = null;
                story = null;
            } else {
                user = extras.getString("username");
                project = extras.getString("projectName");
                sprint = extras.getString("sprintName");
                story = extras.getString("storyName");
            }
        } else {
            user = (String) savedInstanceState.getSerializable("username");
            project = (String) savedInstanceState.getSerializable("projectName");
            sprint = (String) savedInstanceState.getSerializable("sprintName");
            story = (String) savedInstanceState.getSerializable("storyName");
        }
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 2");
        ListView lv = findViewById(R.id.listView2);
        dsDAO = new detailStoryDAO(this,user+project+sprint+story);
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 3");
        stories = new ArrayList<String>();
        dsDAO.getstories(stories);
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 4");
        EditText mEdit;
        mEdit   = (EditText)findViewById(R.id.storyName);
        mEdit.setText(story);
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 5");
        EditText mEdit1;
        mEdit1   = (EditText)findViewById(R.id.Progress);
        mEdit1.setText(stories.get(2).toString());
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 6");
        EditText mEdit2;
        mEdit2   = (EditText)findViewById(R.id.storyDescription);
        mEdit2.setText(stories.get(0).toString().trim());
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 7");
        EditText mEdit3;
        mEdit3   = (EditText)findViewById(R.id.worker);
        mEdit3.setText(stories.get(1).toString());
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 8");

        id = stories.get(3).toString();
        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 9");

    }

    public void onSubmit(View view){

        EditText mEdit;
        mEdit   = (EditText)findViewById(R.id.storyName);
        EditText mEdit1;
        mEdit1   = (EditText)findViewById(R.id.Progress);
        EditText mEdit2;
        mEdit2   = (EditText)findViewById(R.id.storyDescription);
        EditText mEdit3;
        mEdit3   = (EditText)findViewById(R.id.worker);

        String name = mEdit.getText().toString();
        String des = mEdit2.getText().toString();
        String wor = mEdit3.getText().toString();
        String prog = mEdit1.getText().toString();

        Log.i("detailStory",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>working 1");

        dsDAO.updateDetailStory(user+project+sprint+name,des,wor,prog,id);

        Intent intent = new Intent(this,StorylistActivity.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        extras.putString("projectName",project);
        extras.putString("sprintName",sprint);
        intent.putExtras(extras);
        startActivity(intent);

    }


    public void onback(View view){

        Intent intent = new Intent(this,StorylistActivity.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        extras.putString("projectName",project);
        extras.putString("sprintName",sprint);
        intent.putExtras(extras);
        startActivity(intent);

    }

}
