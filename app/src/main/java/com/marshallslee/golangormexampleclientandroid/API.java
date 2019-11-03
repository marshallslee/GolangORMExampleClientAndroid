package com.marshallslee.golangormexampleclientandroid;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST(URLs.STUDENT)
    Call<ResponseBody> addStudent(@Body Student student);
}
