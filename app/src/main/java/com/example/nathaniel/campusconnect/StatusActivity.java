package com.example.nathaniel.campusconnect;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StatusActivity extends AppCompatActivity {

    private Toolbar mToolBar;

    private TextInputLayout mStatusLayout;
    private Button mSaveButton;

    //firebase
    private DatabaseReference mStatusDatabase;
    private FirebaseUser mCurrentUser;


    //progressDialogue
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        //firebase
        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        String current_id = mCurrentUser.getUid();

        mStatusDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        mToolBar = findViewById(R.id.status_appBar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Account Status");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String status_value = getIntent().getStringExtra("status_value");


        //Progress
        mProgress = new ProgressDialog(this);


        mStatusLayout = findViewById(R.id.status_input);
        mSaveButton = findViewById(R.id.status_save_button);

        mStatusLayout.getEditText().setText(status_value);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ProgressDialogue
                mProgress = new ProgressDialog(StatusActivity.this);
                mProgress.setTitle("Saving Changes");
                mProgress.setMessage("Changes being applied");
                mProgress.show();

                String status = mStatusLayout.getEditText().getText().toString();

                mStatusDatabase.child("status").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){

                            mProgress.dismiss();

                        } else {

                            Toast.makeText(getApplicationContext(), "Error in saving changes", Toast.LENGTH_LONG);


                        }

                    }
                });
            }
        });
    }
}
