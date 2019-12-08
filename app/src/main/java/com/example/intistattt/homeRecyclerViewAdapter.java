package com.example.intistattt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intistattt.model.ClassList;

import java.util.List;

public class homeRecyclerViewAdapter extends RecyclerView.Adapter<homeRecyclerViewAdapter.MyViewHolder> {

   Context mContext;
   List<ClassList> mData;

   public homeRecyclerViewAdapter(Context mContext, List<ClassList> mData){
       this.mContext = mContext;
       this.mData = mData;
   }

    @Override
    public void onBindViewHolder(@NonNull homeRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.tv_timeStart.setText(mData.get(position).getTimeStart());
        holder.tv_timeEnd.setText(mData.get(position).getTimeEnd());
        holder.tv_subjectTitle.setText(mData.get(position).getName());
        holder.tv_subjectCode.setText(mData.get(position).getSubjectCode());
        holder.tv_location.setText(mData.get(position).getRoom());

    }

    @NonNull
    @Override
    public homeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v;
       v = LayoutInflater.from(mContext).inflate(R.layout.item_classlist, parent, false);
       MyViewHolder vHolder = new MyViewHolder(v);
       return vHolder;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_timeStart;
        private TextView tv_timeEnd;
        private TextView tv_subjectTitle;
        private TextView tv_subjectCode;
        private TextView tv_location;


        public MyViewHolder(View itemView){
            super(itemView);

            tv_timeStart = itemView.findViewById(R.id.timeStart);
            tv_timeEnd = itemView.findViewById(R.id.timeEnd);
            tv_subjectTitle = itemView.findViewById(R.id.subjectText);
            tv_subjectCode = itemView.findViewById(R.id.subjectCodeText);
            tv_location = itemView.findViewById(R.id.locationText);
        }
    }
}
