package com.example.intistattt.ui.timetable;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.intistattt.ui.home.HomeFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TimetableFragment extends Fragment {

    Query timetable;
    RecyclerView mRecyclerViewMonday;
    private List<ClassList> Classes;
    FirebaseRecyclerAdapter<ClassList, TimetableFragment.myViewHolder> adapter;
    FirebaseRecyclerOptions<ClassList> options;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_timetable, container, false);
        mRecyclerViewMonday = v.findViewById(R.id.recyclerViewMonday);
        mRecyclerViewMonday.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerViewMonday.addItemDecoration(new DividerItemDecoration(getActivity(),1));
        return v;
    }


    @Override
    public void onStart()
    {
        super.onStart();
        timetable = FirebaseDatabase.getInstance().getReference("subject").orderByChild("dayofweek");
        options = new FirebaseRecyclerOptions.Builder<ClassList>()
                .setQuery(timetable, ClassList.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<ClassList, TimetableFragment.myViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TimetableFragment.myViewHolder myViewHolder, int i, @NonNull ClassList classList) {
                myViewHolder.subjectName.setText(classList.getName());
                myViewHolder.subjectCode.setText(classList.getSubjectCode());
                myViewHolder.timeStart.setText(classList.getTimeStart());
                myViewHolder.timeEnd.setText(classList.getTimeEnd());
                myViewHolder.location.setText(classList.getRoom());
                myViewHolder.day.setText(classList.getDay());
            }

            @NonNull
            @Override
            public TimetableFragment.myViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, parent, false);
                final TimetableFragment.myViewHolder viewHolder = new TimetableFragment.myViewHolder(view);
                return viewHolder;
            }
        };

        mRecyclerViewMonday.setAdapter(adapter);
        adapter.startListening();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        TextView subjectName, subjectCode, timeStart, timeEnd, location, day;
        ConstraintLayout item;

        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            this.subjectName = itemView.findViewById(R.id.timetableSubjectText);
            this.subjectCode = itemView.findViewById(R.id.timetableSubjectCodeText);
            this.timeStart = itemView.findViewById(R.id.timetableTimeStart);
            this.timeEnd = itemView.findViewById(R.id.timetableTimeEnd);
            this.location = itemView.findViewById(R.id.timetableLocationText);
            this.day =  itemView.findViewById(R.id.dayOfClass);
            this.item = itemView.findViewById(R.id.timetableList_item);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

