package com.project.android.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.project.android.R;
import com.project.android.adapters.AdminMainFragmentPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class AdminMainActivity extends AppCompatActivity {

    //User Interface
    private Toolbar mToolbar;

    //Firebase
    private FirebaseAuth mAuth;
    private String mCurrentUser;

    private AdminMainFragmentPagerAdapter adminMainFragmentPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private static final String preferences = "Data";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        //Firebase
        mAuth =FirebaseAuth.getInstance();

        //User Interface
        mTabLayout = findViewById(R.id.admin_main_tab_layout);
        mViewPager = findViewById(R.id.admin_main_view_pager);
        mToolbar = findViewById(R.id.admin_main_app_bar_layout);

        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Admin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adminMainFragmentPagerAdapter = new AdminMainFragmentPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        mViewPager.setAdapter(adminMainFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void sendToStart() {
        Intent startIntent = new Intent(AdminMainActivity.this, com.project.android.activities.StartActivity.class);
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
                Intent mainFacultyClassIntent = new Intent(AdminMainActivity.this, com.project.android.activities.FacultyAdminClassActivity.class);
                startActivity(mainFacultyClassIntent);
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
