package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.models.QuizModel;
import com.example.quizapp.models.QuizResult;
import com.example.quizapp.retrofit.ApiService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RuleActivity extends AppCompatActivity implements Serializable {
    List<QuizResult> daftar_soal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        String kategori = getIntent().getStringExtra("kategori");
        String id_kategori = getIntent().getStringExtra("id_kategori");
        TextView txt_judul = findViewById(R.id.txt_judul_kategori);
        txt_judul.setText(kategori);
        getQuestionApi("https://opentdb.com/api.php?amount=5&category=" + id_kategori + "&difficulty=medium&type=multiple");
        Button close = findViewById(R.id.btnClose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        Button start = findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(v.getContext(), QuizActivity.class);
                        intent.putExtra("kategori", kategori);
                        intent.putExtra("id_kategori", id_kategori);
                        intent.putExtra("daftar_soal", (Serializable) daftar_soal);
                        v.getContext().startActivity(intent);
                    }
                }, 3000);
            }
        });
    }

    private void getQuestionApi(String s) {
        ApiService.endpoint().getSoal(s).enqueue(new Callback<QuizModel>() {
            @Override
            public void onResponse(Call<QuizModel> call, Response<QuizModel> response) {
                daftar_soal = response.body().getResults();
                Log.d("daftar_soal", String.valueOf(daftar_soal));
            }

            @Override
            public void onFailure(Call<QuizModel> call, Throwable t) {

            }
        });
    }
}