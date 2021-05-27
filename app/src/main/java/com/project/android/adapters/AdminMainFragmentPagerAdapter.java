package com.project.android.adapters;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.project.android.fragments.AttendanceFragment;
import com.project.android.fragments.PresentFragment;

public class AdminMainFragmentPagerAdapter extends FragmentPagerAdapter {


    private Context mContext;

    public AdminMainFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new PresentFragment();
            case 1:
                return new AttendanceFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Present";
            case 1:
                return "Attendance";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

}
