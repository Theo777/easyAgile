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

public class detailStoryDAO {


    private SQLiteDatabase db;
    private Cursor favoritesCursor;
    private String username;

    public detailStoryDAO(Context context, String user){

        easyAgileDataBaseHelper easyAgileDataBaseHelper = new easyAgileDataBaseHelper(context);
        db = easyAgileDataBaseHelper.getReadableDatabase();
        username = user;
    }

    public void insertDetailStory(String Name,String description,String worker,String status){

        ContentValues story = new ContentValues();
        story.put("NAME",Name);
        story.put("DESCRIPTION",description);
        story.put("WORKER",worker);
        story.put("STATUS",status);
        db.insert("DETAILSTORIES",null,story);


    }

    public void updateDetailStory(String Name,String description,String worker,String status,String id){

        ContentValues story = new ContentValues();
        story.put("NAME",Name);
        Log.i("update detail","/////////////////////////name "+Name);
        story.put("DESCRIPTION",description);
        Log.i("update detail","/////////////////////////description "+description);
        story.put("WORKER",worker);
        Log.i("update detail","/////////////////////////worker "+worker);
        story.put("STATUS",status);
        Log.i("update detail","/////////////////////////status "+status);
        db.update("DETAILSTORIES",story,"_id=?",new String[]{id});


    }

    public void getstories(ArrayList projects){


        favoritesCursor = db.query("DETAILSTORIES",new String[] { "_id", "NAME","DESCRIPTION","WORKER","STATUS"},null,null,null,null,null,null);

        ArrayList projectIds = new ArrayList<>();
        ArrayList projectIds2 = new ArrayList<>();
        ArrayList projectIds3 = new ArrayList<>();
        ArrayList projectIds4 = new ArrayList<>();
        ArrayList projectIds5 = new ArrayList<>();
        Log.i("detailStoryDAO","??????????????????????????????????????????????? before call to Id");
        while(favoritesCursor.moveToNext()){

            String itemId = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("NAME"));
            String itemId2 = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("DESCRIPTION"));
            String itemId3 = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("WORKER"));
            String itemId4 = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("STATUS"));
            String itemId5 = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("_id"));
            projectIds.add(itemId);
            projectIds2.add(itemId2);
            projectIds3.add(itemId3);
            projectIds4.add(itemId4);
            projectIds5.add(itemId5);

            Log.i("detailStoryDAO","???????????????????????????????????? story id "+itemId5);

        }

        int userLength = username.length();
        String temp;


        for (int i = 0; i < projectIds.size() ; i++) {

            temp = projectIds.get(i).toString();
            Log.i("detailStoryDAO","???????????????????????????????????? temp "+temp);
            Log.i("detailStoryDAO","???????????????????????????????????? username "+username);
            Log.i("detailStoryDAO","????????????????????????????????????  userlength "+userLength   );
            temp = temp.substring(0,userLength);
            if(temp.equals(username)){
                temp =  projectIds.get(i).toString().substring(userLength, projectIds.get(i).toString().length());

                projects.add(projectIds2.get(i).toString());
                projects.add(projectIds3.get(i).toString());
                projects.add(projectIds4.get(i).toString());
                projects.add(projectIds5.get(i).toString());
            }

        }

    }


}
