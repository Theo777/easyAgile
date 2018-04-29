package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class messageBoard extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String user;
    private String project;
    private sprintDAO sDAO;
    private ArrayList stories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);
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

        ListView lv = findViewById(R.id.listView);
        sDAO = new sprintDAO(this,user+project);

         stories = new ArrayList<String>();
        sDAO.getsprints(stories);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                stories );

        lv.setAdapter(arrayAdapter);

   /*     AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,
                                            long id) {
                        if (position == 0) {
                            Intent intent = new Intent(messageBoard.this,StorylistActivity.class);
                            startActivity(intent);
                        }
                    }
                };*/
        //Add the listener to the list view
        //ListView listView = (ListView) findViewById(R.id.projectList);
        lv.setOnItemClickListener(this);



    }

    public void onItemClick(AdapterView<?> l, View view, int position, long id) {

            String sprintName = stories.get(position).toString();

            Intent intent = new Intent(messageBoard.this,StorylistActivity.class);
            Bundle extras = new Bundle();
            extras.putString("username",user);
            extras.putString("projectName",project);
            extras.putString("sprintName",sprintName);
            intent.putExtras(extras);
            startActivity(intent);




    }

    public void onbackButton(View view){

        Intent intent = new Intent(this,menuPage.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        intent.putExtras(extras);
        startActivity(intent);

    }

    public void onNewSprint(View view){

        Intent intent = new Intent(this,newSprintActivity.class);
        Bundle extras = new Bundle();
        extras.putString("username",user);
        extras.putString("projectName",project);
        intent.putExtras(extras);
        startActivity(intent);

    }

    //
    /////
    //

    //
}
