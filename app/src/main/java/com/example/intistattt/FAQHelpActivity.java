package com.example.intistattt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intistattt.model.ProblemList;

import java.util.ArrayList;

public class FAQHelpActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq);
        getSupportActionBar().setTitle("Frequently Asked Questions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<ProblemList> qnaList = new ArrayList<>();
        qnaList.add(new ProblemList("Who will see your username?", "Those people who know your studentID, we advise you to create a stronger password right after you got your user account."));
        qnaList.add(new ProblemList("What if I don't like my assigned username?", "Contact our support team here to request a username change."));
        qnaList.add(new ProblemList("Does INTI protect my privacy and keep my information secure?", "We know security and privacy are important to you – and they are important to us, too. We make it a priority to provide strong security and give you confidence that your information is safe and accessible when you need it."));
        qnaList.add(new ProblemList("What can i do when the server is down and it's during class hour?", "Fear not, our server is backed by firebase Google and there is a very low change of server failure. If things happened, be sure to let us know and we will try to fix the issue, at the same time you can inform the lecturer so he/she can take your attendance."));
        qnaList.add(new ProblemList("Can i take my attendance outside of INTI?", "No you cant, in fact, our system is interconnected within the INTI network. INTISTATT still works as intended without INTI wifi like referring to your timetable and check your attendance stats. But not to the extent of taking attendance outside INTI, that's unfair."));

        mRecyclerView = findViewById(R.id.recyclerView_FAQ);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new QNARecyclerAdapter(qnaList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
