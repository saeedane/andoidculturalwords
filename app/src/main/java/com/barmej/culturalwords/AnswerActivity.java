package com.barmej.culturalwords;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {
    int currentIndexAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        currentIndexAnswer = getIntent().getIntExtra(Constant.keyIndexAnswer, 0);
        String[] answers = getResources().getStringArray(R.array.answers);
        String[] answer_description = getResources().getStringArray(R.array.answer_description);
        TextView text_view_answer = findViewById(R.id.text_view_answer);
        text_view_answer.setText(answers[currentIndexAnswer] + " : " + answer_description[currentIndexAnswer]);


    }


    public void back_button(View view) {

        finish();

    }
}
