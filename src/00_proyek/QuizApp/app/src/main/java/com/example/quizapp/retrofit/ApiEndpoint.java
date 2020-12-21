package com.example.quizapp.retrofit;

import com.example.quizapp.models.KategoriModel;
import com.example.quizapp.models.QuizModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiEndpoint {
    @GET("api.php?amount=20&category=27&difficulty=medium&type=multiple")
    Call<QuizModel> getData();

    @GET()
    Call<QuizModel> getSoal(@Url String url);

    @GET("api_category.php")
    Call<KategoriModel> getKategori();

}
