package com.marshallslee.golangormexampleclientandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.marshallslee.golangormexampleclientandroid.adapter.StudentListAdapter;
import com.marshallslee.golangormexampleclientandroid.api.Client;
import com.marshallslee.golangormexampleclientandroid.consts.Consts;
import com.marshallslee.golangormexampleclientandroid.consts.URLs;
import com.marshallslee.golangormexampleclientandroid.listener.OnItemClickListener;
import com.marshallslee.golangormexampleclientandroid.listener.OnItemLongClickListener;
import com.marshallslee.golangormexampleclientandroid.model.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, OnItemLongClickListener {

    private final String TAG = "MainActivity";
    private String firstName, middleName, lastName, studentNumber, gender, major;
    private EditText etFirstName, etMiddleName, etLastName, etStudentNumber;
    private TextView tvGender, tvMajor;
    private Button btnRegister;
    private ArrayList<Student> students = new ArrayList<>();
    private RecyclerView rvStudents;
    private StudentListAdapter studentListAdapter;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        DividerItemDecoration decoration = new DividerItemDecoration(rvStudents.getContext(), layoutManager.getOrientation());
        decoration.setDrawable(getResources().getDrawable(R.drawable.custom_divideritemdecoration));
        rvStudents.addItemDecoration(decoration);
        rvStudents.setLayoutManager(layoutManager);
        rvStudents.setItemAnimator(new DefaultItemAnimator());

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

    @Override
    protected void onResume() {
        super.onResume();

        // student list will be refreshed onResume.
        new LoadStudentsTask().execute();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    private class LoadStudentsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Client client = new Client(URLs.BASE_URL);
            Call<ResponseBody> call = client.getApi().getAllStudents();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if(response.isSuccessful() && response.body() != null) {
                        try {
                            final String strJSON = response.body().string();
                            JSONObject jsonObject = new JSONObject(strJSON);
                            int httpStatus = jsonObject.getInt(Consts.STATUS);

                            switch(httpStatus) {
                                case 200:
                                    JSONArray jsonArray = jsonObject.getJSONArray(Consts.DATA);
                                    if(jsonArray != null && jsonArray.length() != 0) {
                                        for(int i=0; i<jsonArray.length(); i++) {
                                            JSONObject object = jsonArray.getJSONObject(i);
                                            String firstName = object.getString(Consts.FIRST_NAME);
                                            String middleName = object.getString(Consts.MIDDLE_NAME);
                                            String lastName = object.getString(Consts.LAST_NAME);
                                            String studentNumber = object.getString(Consts.STUDENT_NUMBER);
                                            Student student = new Student(firstName, middleName, lastName, studentNumber, null, null);
                                            students.add(student);
                                        }
                                        listStudents(students);
                                    }
                                    break;

                                default:
                                    break;
                            }
                        } catch(JSONException e) {
                            Log.e(TAG, "JSONException caught: " + e.getMessage());
                        } catch(IOException e) {
                            Log.e(TAG, "IOException caught: " + e.getMessage());
                        }
                    } else {
                        Log.e(TAG, "Response is not successful.");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.e(TAG, "Failure on LoadStudentsTask: " + t.getMessage());
                }
            });
            return null;
        }
    }

    private void listStudents(ArrayList<Student> students) {
        rvStudents.removeAllViewsInLayout();
        studentListAdapter = new StudentListAdapter(students, this);
        studentListAdapter.setOnItemClickListener(this);
        rvStudents.setAdapter(studentListAdapter);
        studentListAdapter.notifyDataSetChanged();
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
                    Log.e(TAG, "Failure on RegisterTask: " + t.getMessage());
                }
            });
            return null;
        }
    }
}
