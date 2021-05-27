package com.project.android.adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.project.android.fragments.AttendanceFragment;
import com.project.android.fragments.SubjectFragment;
import com.project.android.fragments.TimeTableFragment;

public class FacultyMainFragmentPagerAdapter extends FragmentPagerAdapter {


    private Context mContext;

    public FacultyMainFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new TimeTableFragment();
            case 1:
                return new SubjectFragment();
            case 2:
                return new AttendanceFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Time Table";
            case 1:
                return "Subject";
            case 2:
                return "Attendance";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
