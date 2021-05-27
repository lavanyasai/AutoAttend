package com.project.android.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.project.android.R;
import com.project.android.models.Students;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PresentFragmentPagerAdapter extends RecyclerView.Adapter<PresentFragmentPagerAdapter.PresentViewHolder> {

    private ArrayList<Students> mStudents = new ArrayList<>();
    private ArrayList<String> mStudentsKeys = new ArrayList<>();
    public String pushId;
    Context mContext;

    public PresentFragmentPagerAdapter(Context context, ArrayList<Students> students, ArrayList<String> studentsKeys, String pushId) {
        this.mContext = context;
        this.mStudents = students;
        this.mStudentsKeys = studentsKeys;
        this.pushId = pushId;
    }

    @NonNull
    @Override
    public PresentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.present_item_view, parent, false);

        return new PresentViewHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull final PresentViewHolder holder, final int position) {
        holder.bind(mStudents.get(holder.getAdapterPosition()), mStudentsKeys.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }


    public class PresentViewHolder extends RecyclerView.ViewHolder {

        View mView;
        private Context mContext;
        private DatabaseReference mImagesDatabaseReference;
        private static final String preferences = "Data";
        SharedPreferences sharedPreferences;

        public PresentViewHolder(View itemView, Context context) {
            super(itemView);
            mView = itemView;
            mContext = context;
            sharedPreferences = context.getSharedPreferences(preferences, Context.MODE_PRIVATE);
            mImagesDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Images").child(sharedPreferences.getString("Class", null)).child(pushId);
        }

        public void bind(Students students, String userKey) {
            String studentFirstName = students.getFirstName();
            String studentLastName = students.getLastName();
            String studentRollNumber = students.getRollNumber();
            String studentImageUrl = students.getImageURL();
            final String studentName = studentFirstName + " " + studentLastName;

            TextView nameTextView = mView.findViewById(R.id.present_student_name);
            nameTextView.setText(studentName);
            TextView rollNumberTextView = mView.findViewById(R.id.present_student_roll_number);
            rollNumberTextView.setText(studentRollNumber);
            CircleImageView studentImageView = mView.findViewById(R.id.present_student_image);
            Picasso.get()
                    .load(studentImageUrl)
                    .placeholder(R.drawable.contact_image)
                    .into(studentImageView);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            final CheckBox presentCheckBox = mView.findViewById(R.id.present_student_check);
            mImagesDatabaseReference.child("Students").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot presentUsersSnapshot : dataSnapshot.getChildren()) {
                        if(studentName.equals(presentUsersSnapshot.getKey())) {
                            Log.v("Hello", "Hello" + presentUsersSnapshot.getValue());
                            presentCheckBox.setChecked(true);
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

}