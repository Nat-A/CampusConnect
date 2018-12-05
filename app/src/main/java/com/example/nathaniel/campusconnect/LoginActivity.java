package com.example.nathaniel.campusconnect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import android.support.design.widget.TextInputLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //toolbar
    private Toolbar mToolBar;

    //initialising login components
    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private Button mLoginButton;

    //we need a progressDialogue so we initialise it
    private ProgressDialog mLoginProgress;

    //the login page has to be verified using firebase Auth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creating the firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        //the toolbar
        mToolBar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Log In"); //the name that must appear on the toolbar

        //defining the ProgressDialogue
        mLoginProgress = new ProgressDialog(this);

        //login
        mLoginEmail = findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
        mLoginButton = findViewById(R.id.login_button);

       //setting the functionality of the login button
        mLoginButton.setOnClickListener(new View.OnClickListener() { //set onClickListener for the login button
            @Override
            public void onClick(View v) {

          //parse the users credentials (email and password to string)
                String email = mLoginEmail.getEditText().getText().toString();
                String password = mLoginPassword.getEditText().getText().toString();

         //if the text boxes are not empty, the dialogue should appear with a message informing the user of logging process
                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Nice smile you have there");
                    mLoginProgress.setCanceledOnTouchOutside(false); //in the event where the user touches the screen during login, the dialogue shouldn't close
                    mLoginProgress.show(); //display the dialogue
                    LoginUser(email, password); //pass the email and password to the LoginUser function

                }
            }
        });
    }

    //Login user method
    private void LoginUser(String email, String password) {

    /*
    * authentication user with the email and password as parameters
    * after clicking, parse the email and password to the firebase auth
     */
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){ //if login successful

                    //stop showing the progressDialog
                    mLoginProgress.dismiss();

    //if verification from the firebase servers is correct, proceed to the main activity page which is the chat page
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class); // intent is set to mainIntent because from the  registration you proceed to the  main page.
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //you bab?
                    startActivity(mainIntent);
                    finish();
                } else{
    //if login failed hide the progress dialogue and display error message
                    mLoginProgress.hide();
                    Toast.makeText(LoginActivity.this, "Error signing in, check your credentials or internet connection", Toast.LENGTH_LONG);
                }


            }
        });



    }
}
