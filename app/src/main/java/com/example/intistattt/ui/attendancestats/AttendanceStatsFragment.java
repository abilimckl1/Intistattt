package com.example.intistattt.ui.attendancestats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intistattt.R;
import com.example.intistattt.model.SubjectList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class AttendanceStatsFragment extends Fragment {

    DatabaseReference mReference1;
    private RecyclerView mRecyclerView1;
    private List<SubjectList> Subjects;
    ArrayList<String> values;
    ArrayList<String> keys;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mReference1 = FirebaseDatabase.getInstance().getReference().child("subject");
        v = inflater.inflate(R.layout.fragment_attendance_stats, container, false);
        mRecyclerView1 = v.findViewById(R.id.recyclerview_attendance_stats);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView1.addItemDecoration(new DividerItemDecoration(getActivity(),1));
        values = getArguments().getStringArrayList("CLASS_ATTENDED");
        keys = getArguments().getStringArrayList("KEY_CLASS");

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseRecyclerOptions<SubjectList> options = new FirebaseRecyclerOptions.Builder<SubjectList>()
                .setQuery(mReference1, SubjectList.class)
                .build();
        FirebaseRecyclerAdapter<SubjectList, AttendanceStatsFragment.myViewHolder> adapter =
                new FirebaseRecyclerAdapter<SubjectList, AttendanceStatsFragment.myViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AttendanceStatsFragment.myViewHolder myViewHolder, int position, @NonNull SubjectList subjectList) {

                        myViewHolder.subjectName.setText(subjectList.getName());
                        myViewHolder.subjectCode.setText(subjectList.getSubjectCode());

                        if(keys.contains(subjectList.getSubjectCode()))
                            for(int i=0; i<keys.size(); i++)
                                if(keys.get(i).equals(subjectList.getSubjectCode()))
                                    myViewHolder.classAttended.setText(values.get(i) + "/" + subjectList.getTotalClassAttendance());
                        else
                            myViewHolder.classAttended.setText("0/" + subjectList.getTotalClassAttendance());

                    }

                    @NonNull
                    @Override
                    public AttendanceStatsFragment.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance_stats, parent, false);
                        AttendanceStatsFragment.myViewHolder viewHolder = new AttendanceStatsFragment.myViewHolder(view);
                        return viewHolder;
                    }
                };

        mRecyclerView1.setAdapter(adapter);
        adapter.startListening();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView subjectName, subjectCode, classAttended;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.subjectName = itemView.findViewById(R.id.titleSubject);
            this.subjectCode = itemView.findViewById(R.id.titleCode);
            this.classAttended = itemView.findViewById(R.id.classAttended);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
