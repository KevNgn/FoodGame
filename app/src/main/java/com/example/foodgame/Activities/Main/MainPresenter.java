package com.example.foodgame.Activities.Main;

import android.support.annotation.NonNull;

import com.example.foodgame.Model.Categories;
import com.example.foodgame.Model.Meals;
import com.example.foodgame.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    //Code modified from ... (That YouTube Video)

    void getMeals() {
        Call<Meals> mealsCall = Util.getApi().getMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call,@NonNull Response<Meals> response) {

                if(response.isSuccessful() && response.body() != null){
                    view.setMeal(response.body().getMeals());
                }
                else{
                    view.onErrorLoading(response.message());
                }
            }
            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }

    void getCategories() {

        Call<Categories> categoriesCall = Util.getApi().getCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call,@NonNull Response<Categories> response) {
                if(response.isSuccessful() && response.body() != null) {
                    view.setCategory(response.body().getCategories());
                }
                else {
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });

    }

}
