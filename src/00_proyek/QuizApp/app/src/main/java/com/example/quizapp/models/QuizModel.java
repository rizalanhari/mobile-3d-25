package com.example.quizapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizModel {

    @SerializedName("response_code")
    private Long mResponseCode;
    @SerializedName("results")
    private List<QuizResult> mResults;

    public Long getResponseCode() {
        return mResponseCode;
    }

    public void setResponseCode(Long responseCode) {
        mResponseCode = responseCode;
    }

    public List<QuizResult> getResults() {
        return mResults;
    }

    public void setResults(List<QuizResult> results) {
        mResults = results;
    }
}
