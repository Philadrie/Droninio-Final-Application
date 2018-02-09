package com.example.adrie.com_gps_wifi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class WelcomeActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageButton nextActivity = findViewById(R.id.drone);
        nextActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View welcome)
            {
                Intent go = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(go);
                finish();
            }
        });
    }
}