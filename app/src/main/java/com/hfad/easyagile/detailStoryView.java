package com.hfad.easyagile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class detailStoryView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story_view);
    }


    public void onback(View view){

        Intent intent = new Intent(this,StorylistActivity.class);

        startActivity(intent);

    }

}
