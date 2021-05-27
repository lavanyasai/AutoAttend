package com.project.android.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.project.android.R;
import com.project.android.adapters.PresentFragmentPagerAdapter;
import com.project.android.models.Students;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PresentFragment extends Fragment {

    private static final int SELECT_PICTURE = 1;
    private StorageReference mStorageReference;
    private DatabaseReference mImagesDatabaseReference;
    private DatabaseReference mUpdatedDatabaseReference;
    private DatabaseReference mStudentsDatabase;
    private RecyclerView mPresentRecyclerView;
    private ProgressBar mPresentProgressBar;
    private ArrayList<Students> mStudentsList;
    private ArrayList<String> mStudentsKeysList;
    private PresentFragmentPagerAdapter presentFragmentPagerAdapter;

    private static final String preferences = "Data";
    SharedPreferences sharedPreferences;

    public PresentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_present, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences(preferences, Context.MODE_PRIVATE);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mStudentsDatabase = FirebaseDatabase.getInstance().getReference().child("Students").child(sharedPreferences.getString("Class", null));
        mImagesDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Images").child(sharedPreferences.getString("Class", null));
        mUpdatedDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Test").child(sharedPreferences.getString("Class", null));

        mStudentsList = new ArrayList<>();
        mStudentsKeysList = new ArrayList<>();
        mPresentProgressBar = view.findViewById(R.id.present_progress_bar);
        mPresentRecyclerView = view.findViewById(R.id.present_recycler_view);
        mPresentRecyclerView.setHasFixedSize(true);
        mPresentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPresentRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Intent chooserIntent = new Intent(Intent.ACTION_GET_CONTENT);
        chooserIntent.setType("image/*");
        startActivityForResult(chooserIntent, SELECT_PICTURE);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            mPresentProgressBar.setVisibility(View.VISIBLE);
            Uri file = data.getData();
            Date date = new Date() ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
            final StorageReference testImagesReference = mStorageReference.child("Test Images").child(dateFormat.format(date)+".jpg");
            assert file != null;
            UploadTask uploadTask = testImagesReference.putFile(file);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        Log.v("PresentFragment" , "Upload Exception="+task.getException());
                    }
                    // Continue with the task to get the download URL
                    return testImagesReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    final String uriString = task.getResult().toString();
                    if (task.isSuccessful()) {
                        DatabaseReference classImagePush = mImagesDatabaseReference.child("ImageURL").push();
                        final String pushId = classImagePush.getKey();
                        mImagesDatabaseReference.child(pushId).child("ImageURL").setValue(uriString);
                        mUpdatedDatabaseReference.child(pushId).child("Updated").setValue("No");
                        mUpdatedDatabaseReference.child(pushId).child("Updated").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.getValue().equals("Yes")) {
                                    mStudentsDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            mStudentsList.clear();
                                            mStudentsKeysList.clear();
                                            for (final DataSnapshot studentsSnapshot : dataSnapshot.getChildren()) {
                                                Students students;
                                                String firstName = studentsSnapshot.child("FirstName").getValue().toString();
                                                String imageURL = studentsSnapshot.child("ImageURL").getValue().toString();
                                                String lastName = studentsSnapshot.child("LastName").getValue().toString();
                                                String rollNumber = studentsSnapshot.child("RollNumber").getValue().toString();
                                                students = new Students(firstName, imageURL, lastName, rollNumber);
                                                mStudentsList.add(students);
                                                mStudentsKeysList.add(studentsSnapshot.getKey());
                                            }
                                            if (!mStudentsList.isEmpty()) {
                                                presentFragmentPagerAdapter = new PresentFragmentPagerAdapter(getContext(), mStudentsList, mStudentsKeysList, pushId);
                                                mPresentRecyclerView.setAdapter(presentFragmentPagerAdapter);
                                                mPresentProgressBar.setVisibility(View.INVISIBLE);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}