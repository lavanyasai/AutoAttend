package com.project.android.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.project.android.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class NavigateActivity extends AppCompatActivity {

    //Firebase
    private String mCurrentUser;
    private FirebaseAuth mAuth;

    private static final String preferences = "Data";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);

        //Firebase
        mAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        if (sharedPreferences == null) {
            Intent mainAdminIntent = new Intent(NavigateActivity.this, StartActivity.class);
            startActivity(mainAdminIntent);
        } else {
            if(sharedPreferences.getString("UserId", null) == null) {
                Intent mainAdminIntent = new Intent(NavigateActivity.this, StartActivity.class);
                startActivity(mainAdminIntent);
                finish();
            } else if(sharedPreferences.contains("Type")) {
                    if (sharedPreferences.getString("Type", null).equals("Admin")) {
                        Intent mainAdminIntent = new Intent(NavigateActivity.this, FacultyAdminClassActivity.class);
                        sharedPreferences.edit().putString("UserId", sharedPreferences.getString("UserId", null)).apply();
                        sharedPreferences.edit().putString("Type", "Admin").apply();
                        startActivity(mainAdminIntent);
                        finish();
                    } else if (sharedPreferences.getString("Type", null).equals("Faculty")) {
                        Intent mainFacultyIntent = new Intent(NavigateActivity.this, FacultyAdminClassActivity.class);
                        sharedPreferences.edit().putString("UserId", sharedPreferences.getString("UserId", null)).apply();
                        sharedPreferences.edit().putString("Type", "Faculty").apply();
                        startActivity(mainFacultyIntent);
                        finish();
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            sendToStart();
        } else {
            mCurrentUser = currentUser.getUid();
        }

    }


    private void sendToStart() {
        Intent startIntent = new Intent(NavigateActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }

}
