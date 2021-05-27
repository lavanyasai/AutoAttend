package com.project.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import com.project.android.R;
import com.project.android.models.TimeTable;

import java.util.ArrayList;

public class TimeTableAdapter extends ArrayAdapter<TimeTable> {

    ArrayList<TimeTable> mTimeTableList;
    Context mContext;

    public TimeTableAdapter(Context context, ArrayList<TimeTable> timetable) {
        super(context, R.layout.edit_time_table_list_view, timetable);
        this.mTimeTableList = timetable;
        this.mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View rowView = inflater.inflate(R.layout.edit_time_table_list_view, null,true);

        final EditText timeEditText = rowView.findViewById(R.id.time_edit_text);
        final EditText subjectEditText = rowView.findViewById(R.id.subject_edit_text);
        ImageButton correctButton = rowView.findViewById(R.id.update_button);

        timeEditText.setText("");
        subjectEditText.setText("");

        correctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeTable timeTable = mTimeTableList.get(position);
                timeTable.setTime(timeEditText.getText().toString());
                timeTable.setSubject(subjectEditText.getText().toString());
            }
        });

        return rowView;
    }


}
