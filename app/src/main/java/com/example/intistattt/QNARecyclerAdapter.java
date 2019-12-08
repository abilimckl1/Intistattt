package com.example.intistattt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.intistattt.model.ProblemList;

import java.util.ArrayList;
public class QNARecyclerAdapter extends RecyclerView.Adapter<QNARecyclerAdapter.MyViewHolder> {

    private ArrayList<ProblemList> mQNAList;


    public QNARecyclerAdapter(ArrayList<ProblemList> qnaList) {
        mQNAList = qnaList;
    }

    @Override
    public void onBindViewHolder(@NonNull QNARecyclerAdapter.MyViewHolder holder, int position) {

        ProblemList currentItem = mQNAList.get(position);
        holder.question.setText(currentItem.getQuestion());
        holder.answer.setText(currentItem.getAnswer());

    }

    @Override
    public int getItemCount() {
        return mQNAList.size();
    }

    @NonNull
    @Override
    public QNARecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View v;
       v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_problem, parent, false);
       MyViewHolder vHolder = new MyViewHolder(v);
       return vHolder;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView question, answer;


        public MyViewHolder(View itemView){
            super(itemView);

            question = itemView.findViewById(R.id.textProblemTitle);
            answer = itemView.findViewById(R.id.textProblemSolution);
        }
    }
}
