package com.hfad.easyagile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Neil on 4/8/2018.
 */

public class easyAgileDataBaseHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "easyAgile"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    easyAgileDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    public boolean checkCreated(SQLiteDatabase db,String tableName){




        Cursor cursor = db.rawQuery(""+tableName+"", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }else {

                this.updateMyDatabase(db,0,0);
                cursor.close();

                return false;
            }
        }else{

            this.updateMyDatabase(db,0,0);
            return false;
        }



    }


    private void  insertUser(SQLiteDatabase db,String username,String password){

        ContentValues userValue = new ContentValues();
        userValue.put("USER", username);
        userValue.put("PASSWORD", password);
        db.insert("USERS",null,userValue);


    }


    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {


        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE USERS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "USER TEXT, "
                    + "PASSWORD TEXT);");

            insertUser(db,"neil","123");

            db.execSQL("CREATE TABLE PROJECTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "USER TEXT, "
                    + "PASSWORD TEXT);");

            db.execSQL("CREATE TABLE SPRINTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "USER TEXT, "
                    + "PASSWORD TEXT);");

            db.execSQL("CREATE TABLE TASKS (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "USER TEXT, "
                    + "PASSWORD TEXT);");

            db.execSQL("CREATE TABLE  (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "USER TEXT, "
                    + "PASSWORD TEXT);");

        }


    }





}
