package com.example.intistattt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intistattt.model.SubjectList;

import org.w3c.dom.Text;

import java.util.List;

public class attendanceRecyclerViewAdapter extends RecyclerView.Adapter<attendanceRecyclerViewAdapter.MyViewHolder> {

   Context mContext;
   List<SubjectList> mData;

   public attendanceRecyclerViewAdapter(Context mContext, List<SubjectList> mData){
       this.mContext = mContext;
       this.mData = mData;
   }

    @Override
    public void onBindViewHolder(@NonNull attendanceRecyclerViewAdapter.MyViewHolder holder, int position) {


        holder.tv_subjectTitle.setText(mData.get(position).getName());
        holder.tv_subjectCode.setText(mData.get(position).getSubjectCode());


    }

    @NonNull
    @Override
    public attendanceRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v;
       v = LayoutInflater.from(mContext).inflate(R.layout.item_attendance_stats, parent, false);
       MyViewHolder vHolder = new MyViewHolder(v);
       return vHolder;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        private TextView tv_subjectTitle;
        private TextView tv_subjectCode;
        private TextView tv_classAttended;



        public MyViewHolder(View itemView){
            super(itemView);

            tv_subjectTitle = itemView.findViewById(R.id.subjectText);
            tv_subjectCode = itemView.findViewById(R.id.subjectCodeText);
            tv_classAttended = itemView.findViewById(R.id.classAttended);

        }
    }
}
