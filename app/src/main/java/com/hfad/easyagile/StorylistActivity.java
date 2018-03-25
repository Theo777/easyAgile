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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storylist);

        ListView lv = findViewById(R.id.listView2);


        List<String> projects = new ArrayList<String>();
        projects.add("1");
        projects.add("2");
        projects.add("3");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                projects );

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
        if (position == 0) {


            Intent intent = new Intent(StorylistActivity.this,detailStoryView.class);
            startActivity(intent);
        }else
        {

        }
    }

    public void onbackButton(View view){

        Intent intent = new Intent(this,messageBoard.class);

        startActivity(intent);

    }

}
