package com.adamapps.retrofitjson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface RedditAPI {

    String BASE_URL = "https://newsapi.org/v2/";

    @GET("top-headlines?country=us&apiKey=9f5c862d1f4f4ef791ad40180322d26f")
    Call<Feed> getArticles();
}
