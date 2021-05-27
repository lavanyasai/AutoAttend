package com.project.android.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.project.android.R;
import com.project.android.adapters.FacultyMainFragmentPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class FacultyMainActivity extends AppCompatActivity {

    //User Interface
    private Toolbar mToolbar;

    private FacultyMainFragmentPagerAdapter facultyMainFragmentPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private static final String preferences = "Data";
    SharedPreferences sharedPreferences;

    //Firebase
    private FirebaseAuth mAuth;
    private String mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_main);

        //Firebase
        mAuth =FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        //User Interface
        mTabLayout = findViewById(R.id.faculty_main_tab_layout);
        mViewPager = findViewById(R.id.faculty_main_view_pager);
        mToolbar = findViewById(R.id.faculty_main_app_bar_layout);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Faculty");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        facultyMainFragmentPagerAdapter = new FacultyMainFragmentPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        mViewPager.setAdapter(facultyMainFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void sendToStart() {
        Intent startIntent = new Intent(FacultyMainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent mainFacultyAdminClassIntent = new Intent(FacultyMainActivity.this, FacultyAdminClassActivity.class);
                startActivity(mainFacultyAdminClassIntent);
                return true;
            case R.id.main_logout:
                mAuth.signOut();
                sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                sendToStart();
        }
        return super.onOptionsItemSelected(item);
    }

}
