package com.marshallslee.golangormexampleclientandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private String firstName, middleName, lastName, studentNumber, gender, major;
    private EditText etFirstName, etMiddleName, etLastName, etStudentNumber;
    private TextView tvGender, tvMajor;
    private Button btnRegister;
    private RecyclerView rvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etLastName = findViewById(R.id.etLastName);
        etStudentNumber = findViewById(R.id.etStudentNumber);
        tvGender = findViewById(R.id.tvGender);
        tvMajor = findViewById(R.id.tvMajor);
        btnRegister = findViewById(R.id.btnRegister);
        rvStudents = findViewById(R.id.rvStudents);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstName = etFirstName.getText().toString();
                middleName = etMiddleName.getText().toString();
                lastName = etLastName.getText().toString();
                studentNumber = etStudentNumber.getText().toString();
                gender = tvGender.getText().toString();
                major = tvMajor.getText().toString();

                if(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(firstName) ||
                        TextUtils.isEmpty(firstName) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(firstName)) {
                    return;
                }

                Student student = new Student(firstName, middleName, lastName, studentNumber, gender, major);
                new RegisterTask().execute(student);
            }
        });
    }

    private class RegisterTask extends AsyncTask<Student, Void, Void> {

        @Override
        protected Void doInBackground(Student... students) {
            Client client = new Client(URLs.BASE_URL);
            Call<ResponseBody> call = client.getApi().addStudent(students[0]);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if(response.isSuccessful()) {

                    } else {
                        Log.e(TAG, "Response is not successful.");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.e(TAG, "Failure: " + t.getMessage());
                }
            });
            return null;
        }
    }
}
