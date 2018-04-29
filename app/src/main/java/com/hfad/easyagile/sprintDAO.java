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

public class sprintDAO {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;
    private String username;

    public sprintDAO(Context context, String user){

        easyAgileDataBaseHelper easyAgileDataBaseHelper = new easyAgileDataBaseHelper(context);
        db = easyAgileDataBaseHelper.getReadableDatabase();
        username = user;
    }

    public void insertsprint(String projectName){

        ContentValues sprint = new ContentValues();
        sprint.put("SPRINT",projectName);
        db.insert("SPRINTS",null,sprint);


    }

    public void getsprints(ArrayList projects){


        favoritesCursor = db.query("SPRINTS",new String[] { "_id", "SPRINT"},null,null,null,null,null,null);

        ArrayList projectIds = new ArrayList<>();


        while(favoritesCursor.moveToNext()){

            String itemId = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("SPRINT"));
            //String itemId2 = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("PASSWORD"));
            projectIds.add(itemId);

            Log.i("userDao","???????????????????????????????????? user id "+itemId);

        }

        int userLength = username.length();
        String temp;


        for (int i = 0; i < projectIds.size() ; i++) {

            temp = projectIds.get(i).toString();
            temp = temp.substring(0,userLength);
            if(temp.equals(username)){
                temp =  projectIds.get(i).toString().substring(userLength, projectIds.get(i).toString().length());
                projects.add(temp);
            }

        }

    }


}
