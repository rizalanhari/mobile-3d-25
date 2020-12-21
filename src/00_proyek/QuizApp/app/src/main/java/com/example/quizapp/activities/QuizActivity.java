package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.models.QuizResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class QuizActivity extends AppCompatActivity implements Serializable {
    Button btn_next;
    RadioGroup rbGroup;
    RadioButton rb, rb2, rb3, rb4;
    String answerd;
    TextView noQuestion;
    int page, score;
    String question;
    String correctAnswer;
    List<String> answer = new ArrayList<String>();
    List<QuizResult> daftar_soal;
    TextView txtQuestion, txtTimer;
    int counter = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setItem();
        page = 1;
        daftar_soal = (List<QuizResult>) getIntent().getSerializableExtra("daftar_soal");
        counter = getIntent().getIntExtra("counter", counter);
        Log.d("daftar_soal_quiz", String.valueOf(daftar_soal));
        setTimer();
        startQuiz();
    }

    private void startQuiz() {
        setPage();
        setSoal();
        onClickAnswerButton();
        onClickNextButton();
    }

    private void setPage() {
        noQuestion.setText(page + "/5");
    }

    private void setTimer() {
        new CountDownTimer(counter * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                txtTimer.setText(String.valueOf(counter));
                counter--;
            }

            public void onFinish() {
                if (answerd == correctAnswer) {
                    score++;
                }
                Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                intent.putExtra("score", score);
                QuizActivity.this.startActivity(intent);
            }
        }.start();
    }

    private void onClickNextButton() {
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (page < 5) {
                    if (answerd == correctAnswer) {
                        score++;
                        Log.d("score", String.valueOf(score));
                    }
                    page++;
                    startQuiz();
                } else {
                    if (answerd == correctAnswer) {
                        score++;
                    }
                    Intent intent = new Intent(v.getContext(), ScoreActivity.class);
                    intent.putExtra("score", score);
                    v.getContext().startActivity(intent);
                }
                Log.d("score", String.valueOf(score));
            }
        });
    }

    private void onClickAnswerButton() {
        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        rb.setBackground(getResources().getDrawable(R.drawable.when_answer_correct));
                        rb2.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        rb3.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        rb4.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        answerd = rb.getText().toString();
                        break;
                    case R.id.radioButton2:
                        rb2.setBackground(getResources().getDrawable(R.drawable.when_answer_correct));
                        rb.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        rb3.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        rb4.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        answerd = rb2.getText().toString();
                        break;
                    case R.id.radioButton3:
                        rb3.setBackground(getResources().getDrawable(R.drawable.when_answer_correct));
                        rb2.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        rb.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        rb4.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        answerd = rb3.getText().toString();
                        break;
                    case R.id.radioButton4:
                        rb4.setBackground(getResources().getDrawable(R.drawable.when_answer_correct));
                        rb2.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        rb3.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        rb.setBackground(getResources().getDrawable(R.drawable.buttons_background));
                        answerd = rb4.getText().toString();
                        break;
                }
            }
        });
    }

    private void setSoal() {
        question = daftar_soal.get(page - 1).getQuestion();
        correctAnswer = daftar_soal.get(page - 1).getCorrectAnswer();
        for (int i = 0; i < 3; i++) {
            answer.add(daftar_soal.get(page - 1).getIncorrectAnswers().get(i));
        }
        answer.add(correctAnswer);
        shuffleAmswer(answer);
    }

    private void shuffleAmswer(List<String> answer) {
        Collections.shuffle(answer);
        setTextQuestionAnswer();
    }

    private void setTextQuestionAnswer() {
        txtQuestion.setText(question);
        rb.setText(answer.get(0));
        rb2.setText(answer.get(1));
        rb3.setText(answer.get(2));
        rb4.setText(answer.get(3));
        rb.setBackground(getResources().getDrawable(R.drawable.buttons_background));
        rb2.setBackground(getResources().getDrawable(R.drawable.buttons_background));
        rb3.setBackground(getResources().getDrawable(R.drawable.buttons_background));
        rb4.setBackground(getResources().getDrawable(R.drawable.buttons_background));
    }

    private void setItem() {
        noQuestion = findViewById(R.id.txtTotalQuestin);
        rbGroup = findViewById(R.id.radioGroup);
        rb = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        txtQuestion = findViewById(R.id.textView);
        btn_next = findViewById(R.id.buttonNext);
        txtTimer = findViewById(R.id.txtTimer);
    }

    @Override
    protected void onPause() {

        super.onPause();
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(QuizActivity.this, "Dilarang Kembali", Toast.LENGTH_LONG).show();
    }

}