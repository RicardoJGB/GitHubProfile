package com.mobileapps.githubprofile.api;

import com.mobileapps.githubprofile.model.Item;
import com.mobileapps.githubprofile.model.ItemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET ("/users/RicardoJGB")
    Call<ItemResponse> getItem();
}

