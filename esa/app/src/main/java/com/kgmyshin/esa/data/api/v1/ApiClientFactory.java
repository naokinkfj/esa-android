/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/02.
 */

package com.kgmyshin.esa.data.api.v1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kgmyshin.esa.data.pref.AccessTokenPreferences;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClientFactory {

    @Inject
    AccessTokenPreferences preferences;

    private static final String BASE_URL = "https://api.esa.io";

    public IApiClient createClient() {
        return createRetrofit().create(IApiClient.class);
    }

    public IApiObservableClient createObservableClient() {
        return createRetrofit().create(IApiObservableClient.class);
    }

    private Retrofit createRetrofit() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-ddTHH:mm:ss+09:00").create();
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().header("Authorization", preferences.getAccessToken()).build();
                return chain.proceed(request);
            }
        });
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
    }

}