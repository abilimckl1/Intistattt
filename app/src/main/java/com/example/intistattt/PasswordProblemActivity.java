package com.example.intistattt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intistattt.model.ProblemList;

import java.util.ArrayList;

public class PasswordProblemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problempassword);
        getSupportActionBar().setTitle("Password Help");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<ProblemList> qnaList = new ArrayList<>();
        qnaList.add(new ProblemList("I do not have my password", "Your username and password will be sent to you once you registered at Level 2 of Inti International College Penang."));
        qnaList.add(new ProblemList("I forgot my password", "By default, your login password would be \"iicp\" followed by your NRIC number in front. If you have changed your password, please come to Level 2 Admin Office and consult with the system admin. He would be glad to lend a hand."));
        qnaList.add(new ProblemList("Does Password Changer work on sites protected by Two-Factor Authentication or security questions?", "Yes. IntiStatt will automatically detect it and prompt you to enter the Two-Factor Authentication code for that site, or the answer to your security questions. None of this information is stored on Firebase's servers."));
        qnaList.add(new ProblemList("Why is a \"suspicious log-in attempt\" displayed on a site after using Password Changer?", "A \"suspicious log-in attempt\" notification may appear when signing in to a site after using Password Changer, because your passwords are changed through our servers when using the Password Changer feature. This is why a site may detect that you are signing in from another location and display a warning. Password Changer uses our servers to actually connect to the sites in order to update your passwords there."));


        mRecyclerView = findViewById(R.id.recyclerViewPassword);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new QNARecyclerAdapter(qnaList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
