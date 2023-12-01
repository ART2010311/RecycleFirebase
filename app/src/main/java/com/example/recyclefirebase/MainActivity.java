package com.example.recyclefirebase;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

// Import Firebase-related libraries

import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.DatabaseError;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<Student> studentArrayList;
DatabaseReference databaseReference;
StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rc);
        databaseReference=FirebaseDatabase.getInstance().getReference("Students");
        studentArrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                studentArrayList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Student student= dataSnapshot.getValue(Student.class);
                    studentArrayList.add(student);
                }
                studentAdapter=new StudentAdapter(MainActivity.this,studentArrayList);
                recyclerView.setAdapter(studentAdapter);
            }

            public void onCancelled(@NonNull DatabaseError error){
                Toast.makeText(MainActivity.this,"Failed to retrieve",Toast.LENGTH_LONG).show();
            }
        });
    }
}