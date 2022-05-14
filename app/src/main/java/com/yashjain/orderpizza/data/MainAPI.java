package com.yashjain.orderpizza.data;

import com.yashjain.orderpizza.model.PizzaDataList;
import com.yashjain.orderpizza.model.Size;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainAPI {
    private static final String url="https://625bbd9d50128c570206e502.mockapi.io/api/v1/pizza/1/";

    public static  getService Service=null;
    public static getService get(){
        if(Service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Service=retrofit.create(getService.class);
        }
        return Service;
    }

    public interface getService{
        @GET(url)
        Call<PizzaDataList> getPizzaList();

    }
}
