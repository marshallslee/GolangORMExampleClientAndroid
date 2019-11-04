package com.marshallslee.golangormexampleclientandroid.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.marshallslee.golangormexampleclientandroid.R;
import com.marshallslee.golangormexampleclientandroid.model.Student;
import com.marshallslee.golangormexampleclientandroid.model.ViewItem;

public class StudentViewHolder extends BaseAdapter.ViewHolder {

    private String fullName;
    private TextView tvFullName;

    public StudentViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvFullName = itemView.findViewById(R.id.tvFullName);
    }

    @Override
    public void bind(ViewItem item) {
        String firstName = ((Student) item).getFirstName();
        String middleName = ((Student) item).getMiddleName();
        String lastName = ((Student) item).getLastName();
        StringBuilder stringBuilder = new StringBuilder();

        if(TextUtils.isEmpty(middleName)) {
            fullName = stringBuilder.append(firstName).append(" ").append(lastName).toString();
        } else {
            fullName = stringBuilder.append(firstName).append(" ").append(middleName).append(" ").append(lastName).toString();
        }
        tvFullName.setText(fullName);
    }
}
