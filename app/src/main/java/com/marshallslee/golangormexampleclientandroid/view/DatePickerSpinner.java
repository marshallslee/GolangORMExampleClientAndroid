package com.marshallslee.golangormexampleclientandroid.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.marshallslee.golangormexampleclientandroid.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePickerSpinner extends DialogFragment {

    private static final String TAG = "DatePickerSpinner";

    static OnPositiveClickListener onPositiveClickListener;

    public static DatePickerSpinner newInstance(String title, OnPositiveClickListener onPositiveClickListener1) {
        onPositiveClickListener = onPositiveClickListener1;
        DatePickerSpinner frag = new DatePickerSpinner();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    public DatePicker datePicker;
    Calendar mCalendar;
    Calendar newDate;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mCalendar = Calendar.getInstance();
        newDate = Calendar.getInstance();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        View v = getActivity().getLayoutInflater().inflate(R.layout.popup_datepicker_spinner, null);
        datePicker = v.findViewById(R.id.datePicker);

        datePicker.init(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Log.d(TAG, "onDateChanged: ");
                newDate.set(year, monthOfYear, dayOfMonth);
                System.out.print(dateFormatter.format(newDate.getTime()));
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String date = newDate.getTime() == null ? dateFormatter.format(mCalendar.getTime()) : dateFormatter.format(newDate.getTime());
                                onPositiveClickListener.onClick(dateFormatter.format(newDate.getTime()));
                            }
                        }
                )
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .create();
    }

    public interface OnPositiveClickListener{
        void onClick(String date);
    }
}
