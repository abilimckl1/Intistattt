package com.example.intistattt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intistattt.model.ProblemList;

import java.util.ArrayList;

public class UsernameProblemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problemusername);
        getSupportActionBar().setTitle("Username Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<ProblemList> qnaList = new ArrayList<>();
        qnaList.add(new ProblemList("I do not have my Username", "Your username and password will be sent to you once you registered at Level 2 of Inti International College Penang."));
        qnaList.add(new ProblemList("I forgot my Username", "Your username would be your Inti student code, simple as that. If you forgot your student ID or lost your student card, you can refer from your Head of Programme, the will be glad to help you."));
        qnaList.add(new ProblemList("Is there anyway of change my Username?", "You cannot change your username by yourself. Please send an email to help@inti.edu.my Alumni Association staff will change it for you."));


        mRecyclerView = findViewById(R.id.recyclerViewUsername);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new QNARecyclerAdapter(qnaList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
