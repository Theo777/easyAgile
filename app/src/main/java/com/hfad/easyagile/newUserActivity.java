package com.hfad.easyagile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newUserActivity extends AppCompatActivity {

    private userDAO udao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        udao = new userDAO(this);
    }

    public void onbackButton(View view){

        Intent intent = new Intent(this,homePage.class);

        startActivity(intent);

    }

    public void onsubmitUser(View view){
        EditText mEdit;
        EditText mEdit2;
        EditText mEdit3;
        mEdit   = (EditText)findViewById(R.id.enterUser);
        mEdit2   = (EditText)findViewById(R.id.enterPassword);
        mEdit3   = (EditText)findViewById(R.id.confirmPassword);

        String user = mEdit.getText().toString();
        String password = mEdit2.getText().toString();
        String confirmPassword = mEdit3.getText().toString();



        if(password.equals(confirmPassword)) {

            boolean available = udao.availableUser(user);




            if(available){
                udao.insertUser(user, password);





                Intent intent = new Intent(this, homePage.class);
                startActivity(intent);
            }else{
                Toast toast = Toast.makeText(this, "username is already taken", Toast.LENGTH_SHORT);
                toast.show();
            }
        }else
        {
            Toast toast = Toast.makeText(this, "passwords do not equal each other", Toast.LENGTH_SHORT);
            toast.show();

        }
    }

}
