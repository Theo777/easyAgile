package com.hfad.easyagile;



import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Neil on 4/8/2018.
 */

public class userDAO {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;
    private boolean created;

    public userDAO(Context context){
        easyAgileDataBaseHelper easyAgileDataBaseHelper = new easyAgileDataBaseHelper(context);
        db = easyAgileDataBaseHelper.getReadableDatabase();
        /*created = easyAgileDataBaseHelper.checkCreated(db,"user");

        if(created){
            Toast toast = Toast.makeText(context,"returned true",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Toast toast = Toast.makeText(context, "returned false", Toast.LENGTH_SHORT);
            toast.show();
        }*/

    }

    public void insertUser(String user, String password){
        ContentValues userValue = new ContentValues();
        userValue.put("USER", user);
        userValue.put("PASSWORD", password);
        db.insert("USERS",null,userValue);


    }

    public boolean availableUser(String user){
        boolean available = true;
        favoritesCursor = db.query("USERS",new String[] { "_id", "USER","PASSWORD"},null,null,null,null,null,null);


        while(favoritesCursor.moveToNext()){

            String itemId = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("USER"));
            if(itemId.equals(user)){
                available = false;
            }

        }

        return available;
    }

    public boolean validUser(Context context,String user,String password){
        int colCount;
        int collCount;
        boolean valid;


        favoritesCursor = db.query("USERS",new String[] { "_id", "USER","PASSWORD"},null,null,null,null,null,null);
        colCount = favoritesCursor.getCount();
        collCount = favoritesCursor.getColumnCount();
        Log.i("userDao","?????????????????????????????????????????? colCount "+colCount);
        Log.i("userDao","?????????????????????????????????????????? collCount " +collCount);
        //String data = favoritesCursor.getColumnName(0);

        ArrayList userIds = new ArrayList<>();
        ArrayList passIds = new ArrayList<>();

        while(favoritesCursor.moveToNext()){

            String itemId = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("USER"));
            String itemId2 = favoritesCursor.getString(favoritesCursor.getColumnIndexOrThrow("PASSWORD"));
            userIds.add(itemId);
            passIds.add(itemId2);
            //Log.i("userDao","?????????????????????????????????????????? "+data);
            Log.i("userDao","???????????????????????????????????? user id "+itemId);
            Log.i("userDao","???????????????????????????????????? pass id "+itemId2);

        }

        for (int i = 0; i <userIds.size() ; i++) {

            Log.i("userDao","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! user "+user);
            Log.i("userDao","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!user id "+userIds.get(i));
            Log.i("userDao","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!password "+password);
            Log.i("userDao","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!pass Ids "+passIds.get(i));
            Log.i("userDao","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! users equal "+(user.equals(userIds.get(i))));
            Log.i("userDao","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! pass equal "+(password.equals(passIds.get(i))));


            if(user.equals(userIds.get(i))&&password.equals(passIds.get(i))){
                return true;
            }



        }



        Toast toast = Toast.makeText(context, "returned count of "+ colCount+" ", Toast.LENGTH_SHORT);
        toast.show();

        return false;
    }


}
