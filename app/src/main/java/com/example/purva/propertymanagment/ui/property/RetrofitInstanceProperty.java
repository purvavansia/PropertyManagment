package com.example.purva.propertymanagment.ui.property;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by purva on 4/26/18.
 */

public class RetrofitInstanceProperty {

    final static String BASE_URL = "http://rjtmobile.com/aamir/property-mgmt/";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;

    }
}
