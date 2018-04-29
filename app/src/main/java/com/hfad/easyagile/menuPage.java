package com.hfad.easyagile;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class menuPage extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private projectDAO pDAO;
    private String user;
    private  ArrayList projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
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

        pDAO = new projectDAO(this,user);

        ListView lv = findViewById(R.id.projectList);


         projects = new ArrayList<String>();
        pDAO.getProjects(projects);



//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
  //              this,
    //            android.R.layout.simple_list_item_1,
      //          projects );

        lv.setAdapter(new ArrayAdapter<String>(menuPage.this,android.R.layout.simple_list_item_1,
                projects));

      //  AdapterView.OnItemClickListener itemClickListener =
        //        new AdapterView.OnItemClickListener(){
          //          @Override
            //        public void onItemClick(AdapterView<?> listView,
              //                              View itemView,
                //                            int position,
                  //                          long id) {
                    //    if (position == 0) {
                      //      Intent intent = new Intent(menuPage.this,messageBoard.class);
                        //    startActivity(intent);
                        //}
                    //}
                //};
        //Add the listener to the list view

        lv.setOnItemClickListener(this);


    }

    public void onItemClick(AdapterView<?> l, View view, int position, long id) {

            String projectName = projects.get(position).toString();

            Intent intent = new Intent(menuPage.this,messageBoard.class);
            Bundle extras = new Bundle();
            extras.putString("username",user);
            extras.putString("projectName",projectName);
            intent.putExtras(extras);
            startActivity(intent);

    }

    public void learnAgile(View view){
        Intent intent = new Intent(menuPage.this,firstLearn.class);
        startActivity(intent);


    }
    public void logOut(View view){
        Intent intent = new Intent(menuPage.this,homePage.class);
        startActivity(intent);
    }


    public void onNewProject(View view) {
        Intent intent = new Intent(menuPage.this,newProject.class);
        intent.putExtra("username",user);
        startActivity(intent);
    }
}
