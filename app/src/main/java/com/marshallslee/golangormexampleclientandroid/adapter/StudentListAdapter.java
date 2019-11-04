package com.marshallslee.golangormexampleclientandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marshallslee.golangormexampleclientandroid.R;

import java.util.ArrayList;

public class StudentListAdapter extends BaseAdapter {

    private Context context;

    public StudentListAdapter(ArrayList dataSet, Context context) {
        super(dataSet, context);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.item_student_list, parent, false);
        return new StudentViewHolder(view);
    }
}
