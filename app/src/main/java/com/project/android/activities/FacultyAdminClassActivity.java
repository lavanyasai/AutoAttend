package com.project.android.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.android.R;

public class FacultyAdminClassActivity extends AppCompatActivity {

    //User Interface
    private Toolbar mToolbar;
    private ListView mListView;

    private int STORAGE_PERMISSION_CODE = 1;

    String[] classList = {"CSE-A", "CSE-B", "CSE-C", "CSE-D"};

    private static final String preferences = "Data";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_admin_class);

        if(Build.VERSION.SDK_INT > 22) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }

        //User Interface
        mListView=findViewById(R.id.faculty_admin_main_list_view);
        mToolbar = findViewById(R.id.faculty_admin_app_bar_layout);

        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Class");

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, classList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(sharedPreferences.getString("Type", null).equals("Faculty")) {
                    Intent mainFacultyClassIntent = new Intent(FacultyAdminClassActivity.this, FacultyMainActivity.class);
                    sharedPreferences.edit().putString("UserId", sharedPreferences.getString("UserId", null)).apply();
                    sharedPreferences.edit().putString("Type", sharedPreferences.getString("Type", null)).apply();
                    sharedPreferences.edit().putString("Class", (parent.getItemAtPosition(position)).toString()).apply();
                    startActivity(mainFacultyClassIntent);
                }
                else {
                    Intent mainAdminClassIntent = new Intent(FacultyAdminClassActivity.this, AdminMainActivity.class);
                    sharedPreferences.edit().putString("UserId", sharedPreferences.getString("UserId", null)).apply();
                    sharedPreferences.edit().putString("Type", sharedPreferences.getString("Type", null)).apply();
                    sharedPreferences.edit().putString("Class", (parent.getItemAtPosition(position)).toString()).apply();
                    startActivity(mainAdminClassIntent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent startMain = new Intent(Intent.ACTION_MAIN); 
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }

}
