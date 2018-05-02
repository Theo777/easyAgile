package com.hfad.easyagile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Neil on 4/12/2018.
 */

public class projectDAO{

    private SQLiteDatabase db;
    private Cursor favoritesCursor;
    private String username;

    public projectDAO(Context context,String user){

        easyAgileDataBaseHelper easyAgileDataBaseHelper = new easyAgileDataBaseHelper(context);
        db = easyAgileDataBaseHelper.getReadableDatabase();
        username = user;
    }

    public void insertproject(String projectName){

        ContentValues projects = new ContentValues();
        projects.put("PROJECT",projectName);
        db.insert("PROJECTS",null,projects);
        Log.i("insertproject","has been inserted");


    }

    public void getProjects(ArrayList projects){


        favoritesCursor = db.query("PROJECTS",new String[] { "_id", "PROJECT"},null,null,null,null,null,null);

        ArrayList projectIds = new ArrayList<>();


        while(favoritesCursor.moveToNext()){

            String itemId = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("PROJECT"));
            //String itemId2 = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("PASSWORD"));
            projectIds.add(itemId);

            Log.i("getProjects","???????????????????????????????????? itemId "+itemId);

        }

        int userLength = username.length();
        String temp;


        for (int i = 0; i < projectIds.size() ; i++) {

            temp = projectIds.get(i).toString();
            if(temp.length()>userLength) {
                temp = temp.substring(0, userLength);
            }

            Log.i("getProjects","????????????? username " + username);
            Log.i("getProjects","?????????????     temp " + temp);
            if(temp.equals(username)){

                temp =  projectIds.get(i).toString().substring(userLength, projectIds.get(i).toString().length());
                projects.add(temp);
                Log.i("getProjects","????????? evaluated to true");
            }

        }

    }




}
