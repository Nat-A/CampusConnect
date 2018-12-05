package com.example.nathaniel.campusconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    //Start
    private Button mRegisterBtn;
    private Button mExistingUserBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mRegisterBtn = (Button) findViewById(R.id.start_register_btn);

    //when user clicks on the Register button, he should be redirected to the Register page
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(registerIntent);

            }
        });


        mExistingUserBtn = (Button) findViewById(R.id.start_existingUser);

    //when user clicks on the already user button, he should be redirected to the login page
        mExistingUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent existingUserIntent = new Intent(StartActivity.this, LoginActivity.class);
                startActivity(existingUserIntent);


            }
        });
    }


}
