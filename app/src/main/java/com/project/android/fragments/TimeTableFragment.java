package com.project.android.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTableFragment extends Fragment {

    private View mMondayInputView;
    private View mTuesdayInputView;
    private View mWednesdayInputView;
    private View mThursdayInputView;
    private View mFridayInputView;
    private View mSaturdayInputView;

    private View mMondayWeekView;
    private View mTuesdayWeekView;
    private View mWednesdayWeekView;
    private View mThursdayWeekView;
    private View mFridayWeekView;
    private View mSaturdayWeekView;


    public TimeTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.fragment_time_table, container, false);

        mMondayInputView = mView.findViewById(R.id.monday_input_time_table);
        mTuesdayInputView = mView.findViewById(R.id.tuesday_input_time_table);
        mWednesdayInputView = mView.findViewById(R.id.wednesday_input_time_table);
        mThursdayInputView = mView.findViewById(R.id.thursday_input_time_table);
        mFridayInputView = mView.findViewById(R.id.friday_input_time_table);
        mSaturdayInputView = mView.findViewById(R.id.saturday_input_time_table);

        mMondayWeekView = mView.findViewById(R.id.monday_week_time_table);
        mTuesdayWeekView = mView.findViewById(R.id.tuesday_week_time_table);
        mWednesdayWeekView = mView.findViewById(R.id.wednesday_week_time_table);
        mThursdayWeekView = mView.findViewById(R.id.thursday_week_time_table);
        mFridayWeekView = mView.findViewById(R.id.friday_week_time_table);
        mSaturdayWeekView = mView.findViewById(R.id.saturday_week_time_table);

        TextView mondayTextView = mMondayWeekView.findViewById(R.id.week_text_view);
        mondayTextView.setText(getResources().getString(R.string.monday));

        TextView tuesdayTextView = mTuesdayWeekView.findViewById(R.id.week_text_view);
        tuesdayTextView.setText(getResources().getString(R.string.tuesday));

        TextView wednesdayTextView = mWednesdayWeekView.findViewById(R.id.week_text_view);
        wednesdayTextView.setText(getResources().getString(R.string.wednesday));

        TextView thursdayTextView = mThursdayWeekView.findViewById(R.id.week_text_view);
        thursdayTextView.setText(getResources().getString(R.string.thursday));

        TextView fridayTextView = mFridayWeekView.findViewById(R.id.week_text_view);
        fridayTextView.setText(getResources().getString(R.string.friday));

        TextView saturdayTextView = mSaturdayWeekView.findViewById(R.id.week_text_view);
        saturdayTextView.setText(getResources().getString(R.string.saturday));

        mMondayWeekView.findViewById(R.id.add_row_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMondayInputView.getVisibility() == View.VISIBLE) {
                    mMondayInputView.setVisibility(View.GONE);
                }
                else {
                    mMondayInputView.setVisibility(View.VISIBLE);
                }
            }
        });

        mTuesdayWeekView.findViewById(R.id.add_row_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTuesdayInputView.getVisibility() == View.VISIBLE) {
                    mTuesdayInputView.setVisibility(View.GONE);
                }
                else {
                    mTuesdayInputView.setVisibility(View.VISIBLE);
                }
            }
        });

        mWednesdayWeekView.findViewById(R.id.add_row_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mWednesdayInputView.getVisibility() == View.VISIBLE) {
                    mWednesdayInputView.setVisibility(View.GONE);
                }
                else {
                    mWednesdayInputView.setVisibility(View.VISIBLE);
                }
            }
        });

        mThursdayWeekView.findViewById(R.id.add_row_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mThursdayInputView.getVisibility() == View.VISIBLE) {
                    mThursdayInputView.setVisibility(View.GONE);
                }
                else {
                    mThursdayInputView.setVisibility(View.VISIBLE);
                }
            }
        });

        mFridayWeekView.findViewById(R.id.add_row_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFridayInputView.getVisibility() == View.VISIBLE) {
                    mFridayInputView.setVisibility(View.GONE);
                }
                else {
                    mFridayInputView.setVisibility(View.VISIBLE);
                }
            }
        });

        mSaturdayWeekView.findViewById(R.id.add_row_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSaturdayInputView.getVisibility() == View.VISIBLE) {
                    mSaturdayInputView.setVisibility(View.GONE);
                }
                else {
                    mSaturdayInputView.setVisibility(View.VISIBLE);
                }
            }
        });

        return mView;
    }

}
