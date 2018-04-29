package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StorylistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String user;
    private String project;
    private String sprint;
    private storyDAO sDAO;
    private ArrayList stories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storylist);
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


        ListView lv = findViewById(R.id.listView2);
        sDAO = new storyDAO(this,user+project+sprint);

        stories = new ArrayList<String>();
        sDAO.getstories(stories);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                stories );

        lv.setAdapter(arrayAdapter);

        /*AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,
                                            long id) {
                        if (position == 0) {
                            Intent intent = new Intent(StorylistActivity.this,detailStoryView.class);
                            startActivity(intent);
                        }
                    }
                };
        //Add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.projectList);*/
        lv.setOnItemClickListener(this);
    }


    public void onItemClick(AdapterView<?> l, View view, int position, long id) {

        //error here to fix crashes upon item click
        String storyName = stories.get(position).toString();

        Intent intent = new Intent(this,detailStoryView.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        extras.putString("projectName",project);
        extras.putString("sprintName",sprint);
        extras.putString("storyName",storyName);
        intent.putExtras(extras);
        startActivity(intent);






    }

    public void onbackButton(View view){

        Intent intent = new Intent(this,messageBoard.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        extras.putString("projectName",project);
        extras.putString("sprintName",sprint);
        intent.putExtras(extras);
        startActivity(intent);

    }

    public void onNewStory(View view){

        Intent intent = new Intent(this,newStoryActivity.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        extras.putString("projectName",project);
        extras.putString("sprintName",sprint);
        intent.putExtras(extras);

        startActivity(intent);

    }

}
