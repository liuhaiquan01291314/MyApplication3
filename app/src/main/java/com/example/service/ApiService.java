package com.example.service;

import android.database.Observable;

import com.example.bean.ProjectBean;

import retrofit2.http.GET;


public interface ApiService {
    String PROJECT_URL="http://static.owspace.com/";
    @GET("?c=api&a=getList&page_id=0")
    Observable<ProjectBean> getData();
}
