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

public class messageBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board);


        ListView lv = findViewById(R.id.projectList);


        List<String> projects = new ArrayList<String>();
        projects.add("get sail");
        projects.add("buy apple watch");
        projects.add("Invite Theo");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                projects );

        lv.setAdapter(arrayAdapter);

        AdapterView.OnItemClickListener itemClickListener =
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
                };
        //Add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.projectList);
        listView.setOnItemClickListener(itemClickListener);



    }

    public void onbackButton(View view){

        Intent intent = new Intent(this,menuPage.class);

        startActivity(intent);

    }
    //
    /////
    //

    //
}
