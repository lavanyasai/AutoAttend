package com.project.android.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.android.R;


public class StartActivity extends AppCompatActivity {

    //User Interface
    private Button mFacultyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mFacultyButton = findViewById(R.id.start_faculty);

        mFacultyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent facultyIntent = new Intent(StartActivity.this, FacultyActivity.class);
                startActivity(facultyIntent);
            }

        });
    }
}
