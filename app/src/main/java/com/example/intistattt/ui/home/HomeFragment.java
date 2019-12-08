package com.example.intistattt.ui.home;

import android.app.Dialog;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.intistattt.R;
import com.example.intistattt.model.ClassList;
import com.example.intistattt.homeRecyclerViewAdapter;
import com.example.intistattt.model.SubjectList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    DatabaseReference setAttendance;
    Query dayClass;
    Calendar calendar = Calendar.getInstance();
    Date date = calendar.getTime();
    String currentDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
    TextView textDay;
    RecyclerView mRecyclerView1;
    List<ClassList> Classes;
    Dialog homeDialog;
    Button buttonYes, buttonNo;
    ArrayList<String> values;
    ArrayList<String> keys;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //set day and decorate and display each item in the recyclerview
        v = inflater.inflate(R.layout.fragment_home, container, false);
        textDay = v.findViewById(R.id.textDay);
        textDay.setText(currentDay);
        mRecyclerView1 = v.findViewById(R.id.recyclerview_class_today);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(),1));
        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        //filter out today's class using setquery and to be displayed in the recyclerview
        dayClass = FirebaseDatabase.getInstance().getReference("subject").orderByChild("day").equalTo(currentDay);
        FirebaseRecyclerOptions<ClassList> options = new FirebaseRecyclerOptions.Builder<ClassList>()
                .setQuery(dayClass, ClassList.class)
                .build();

        FirebaseRecyclerAdapter<ClassList, myViewHolder> adapter =
                new FirebaseRecyclerAdapter<ClassList, myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position, @NonNull final ClassList classList) {
                setAttendance = FirebaseDatabase.getInstance().getReference("student").child("p17008797").child("classattended").child(classList.getSubjectCode());
                //set text to each of the variables in the item to be displayed in the recyclerview
                myViewHolder.subjectName.setText(classList.getName());
                myViewHolder.subjectCode.setText(classList.getSubjectCode());
                myViewHolder.timeStart.setText(classList.getTimeStart());
                myViewHolder.timeEnd.setText(classList.getTimeEnd());
                myViewHolder.location.setText(classList.getRoom());
            }

            @NonNull
            @Override
            public myViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classlist, parent, false);
                final myViewHolder viewHolder = new myViewHolder(view);
                 //Setting up HomeDialog
                homeDialog = new Dialog(parent.getContext());
                homeDialog.setContentView(R.layout.dialog_take_attendance);
                buttonNo = homeDialog.findViewById(R.id.buttonNo);
                buttonYes = homeDialog.findViewById(R.id.buttonYes);
                //HomeDialog to be popped up when each of the item is clicked
                viewHolder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homeDialog.show();
                    }
                });

                //When Yes button clicked, +1 to the current attendance of the subject
                buttonYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setAttendance.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                long classAttended = (long) dataSnapshot.getValue();
                                setAttendance.setValue(classAttended+1);
                                homeDialog.dismiss();
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                });
                //when No button clicked, close dialog.
                buttonNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        homeDialog.dismiss();
                    }
                });
                return viewHolder;
            }
        };

        //setting adapter to listen to retrieve all the data from Firebase
        mRecyclerView1.setAdapter(adapter);
        adapter.startListening();

    }
    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView subjectName, subjectCode, timeStart, timeEnd, location;
        ConstraintLayout item;


        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //initialize variables to link to xml components to be set text later
            this.item = itemView.findViewById(R.id.classList_item);
            this.subjectName = itemView.findViewById(R.id.subjectText);
            this.subjectCode = itemView.findViewById(R.id.subjectCodeText);
            this.timeStart = itemView.findViewById(R.id.timeStart);
            this.timeEnd = itemView.findViewById(R.id.timeEnd);
            this.location = itemView.findViewById(R.id.locationText);
        }
    }
}
