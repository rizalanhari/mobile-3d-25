package com.example.quizapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KategoriModel {
    @SerializedName("trivia_categories")
    private List<TriviaCategoryModel> mTriviaCategories;

    public List<TriviaCategoryModel> getTriviaCategories() {
        return mTriviaCategories;
    }

    public void setTriviaCategories(List<TriviaCategoryModel> triviaCategories) {
        mTriviaCategories = triviaCategories;
    }
}
