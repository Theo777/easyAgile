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

public class storyDAO {


    private SQLiteDatabase db;
    private Cursor favoritesCursor;
    private String username;

    public storyDAO(Context context, String user){

        easyAgileDataBaseHelper easyAgileDataBaseHelper = new easyAgileDataBaseHelper(context);
        db = easyAgileDataBaseHelper.getReadableDatabase();
        username = user;
    }

    public void insertstory(String projectName){

        ContentValues story = new ContentValues();
        story.put("STORY",projectName);
        db.insert("STORIES",null,story);


    }

    public void getstories(ArrayList projects){


        favoritesCursor = db.query("STORIES",new String[] { "_id", "STORY"},null,null,null,null,null,null);

        ArrayList projectIds = new ArrayList<>();


        while(favoritesCursor.moveToNext()){

            String itemId = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("STORY"));
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
