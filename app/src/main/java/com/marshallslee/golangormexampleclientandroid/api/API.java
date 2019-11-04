package com.marshallslee.golangormexampleclientandroid.api;

import com.marshallslee.golangormexampleclientandroid.consts.URLs;
import com.marshallslee.golangormexampleclientandroid.model.Student;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @POST(URLs.STUDENT)
    Call<ResponseBody> addStudent(@Body Student student);

    @GET(URLs.STUDENT)
    Call<ResponseBody> getAllStudents();
}
