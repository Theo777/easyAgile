package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class homePage extends AppCompatActivity{

    private userDAO udao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        udao = new userDAO(this);
    }

    public void onsubmitButton(View view){
    boolean validUser;
        EditText mEdit;
        EditText mEdit2;

        mEdit   = (EditText)findViewById(R.id.userName);
        mEdit2   = (EditText)findViewById(R.id.editText2);

        String user;
        String password;

         user = mEdit.getText().toString();
         password= mEdit2.getText().toString();

    validUser = udao.validUser(this,user,password);

    if(validUser) {


        Intent intent = new Intent(this, menuPage.class);
        intent.putExtra("username",user);
        startActivity(intent);

    }else{

        Toast toast = Toast.makeText(this, "username not valid", Toast.LENGTH_SHORT);
        toast.show();

    }

    }

    public void onnewUser(View view){

        Intent intent = new Intent(this,newUserActivity.class);

        startActivity(intent);

    }

}
