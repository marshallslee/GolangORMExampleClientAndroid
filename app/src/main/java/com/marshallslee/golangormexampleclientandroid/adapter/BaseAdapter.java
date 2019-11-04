package com.marshallslee.golangormexampleclientandroid.adapter;

import android.content.Context;
import android.view.View;

import com.marshallslee.golangormexampleclientandroid.adapter.BaseAdapter.ViewHolder;
import com.marshallslee.golangormexampleclientandroid.listener.OnItemClickListener;
import com.marshallslee.golangormexampleclientandroid.listener.OnItemLongClickListener;
import com.marshallslee.golangormexampleclientandroid.model.ViewItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public abstract class BaseAdapter<T extends ViewItem> extends RecyclerView.Adapter<ViewHolder> {

    private int numType;
    private ArrayList<ViewItem> dataSet;
    private static OnItemClickListener onItemClickListener;
    private static OnItemLongClickListener onItemLongClickListener;
    private Context context;

    BaseAdapter(ArrayList<ViewItem> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
        numType = dataSet.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        BaseAdapter.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        BaseAdapter.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position).getListItemType();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ViewItem object = dataSet.get(position);
        viewHolder.bind(object);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public abstract static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(itemView);
        }

        public abstract void bind(ViewItem item);

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(v, getAdapterPosition());
            }
            return true;
        }
    }
}
