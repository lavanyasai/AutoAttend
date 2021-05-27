package com.project.android.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.project.android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FacultyActivity extends AppCompatActivity {

    //User Interface
    private EditText mEmail;
    private EditText mPassword;
    private Button mLogin;
    private Toolbar mToolbar;
    private RadioGroup mAdminFacultyGroup;
    private ProgressBar mProgressBar;

    //Firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mFacultyDatabase;

    private static final String preferences = "Data";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        //User Interface
        mEmail = findViewById(R.id.faculty_input_user_email);
        mPassword = findViewById(R.id.faculty_input_user_password);
        mLogin = findViewById(R.id.faculty_login_button);
        mToolbar = findViewById(R.id.faculty_app_bar_layout);
        mProgressBar = findViewById(R.id.faculty_login_progress_bar);
        mAdminFacultyGroup = findViewById(R.id.faculty_admin_group);

        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Faculty");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        mFacultyDatabase = FirebaseDatabase.getInstance().getReference().child("Faculty");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Faculty");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgressBar.setVisibility(View.VISIBLE);
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            mProgressBar.setVisibility(View.VISIBLE);
                            RadioButton radioButton = mAdminFacultyGroup.findViewById(mAdminFacultyGroup.getCheckedRadioButtonId());
                            if(radioButton.getText().equals("Admin")) {
                                Intent mainAdminIntent = new Intent(FacultyActivity.this, FacultyAdminClassActivity.class);
                                sharedPreferences.edit().putString("UserId", mAuth.getCurrentUser().getUid()).apply();
                                sharedPreferences.edit().putString("Type", "Admin").apply();
                                startActivity(mainAdminIntent);
                                finish();
                            }
                            else if(radioButton.getText().equals("Faculty")) {
                                Intent mainFacultyIntent = new Intent(FacultyActivity.this, FacultyAdminClassActivity.class);
                                sharedPreferences.edit().putString("UserId", mAuth.getCurrentUser().getUid()).apply();
                                sharedPreferences.edit().putString("Type", "Faculty").apply();
                                startActivity(mainFacultyIntent);
                                finish();
                            }
                        }

                        else {
                            mProgressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(FacultyActivity.this,"Sorry!!!Please Try Again",Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.INVISIBLE);
                            Log.v("Faculty Activity","Exception="+task.getException());

                        }

                    }

                });
            }
        });
    }
}
