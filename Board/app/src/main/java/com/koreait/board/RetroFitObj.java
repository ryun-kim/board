package com.koreait.board;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitObj {
    public static Retrofit rfo;
    private RetroFitObj() {}

    public static Retrofit getInstance() {
        if(rfo == null){
            rfo = new Retrofit.Builder()
                    .baseUrl("http://192.168.219.103:8090/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return rfo;
    }
}
