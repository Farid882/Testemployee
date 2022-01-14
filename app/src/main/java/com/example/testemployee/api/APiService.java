package com.example.testemployee.api;

import com.example.testemployee.pojo.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APiService {
    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees();
}
