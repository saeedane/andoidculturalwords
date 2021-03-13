package com.barmej.culturalwords;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int[] heritage_Image = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3,
            R.drawable.icon_4, R.drawable.icon_5, R.drawable.icon_6, R.drawable.icon_7,
            R.drawable.icon_8, R.drawable.icon_9, R.drawable.icon_10, R.drawable.icon_11,
            R.drawable.icon_12, R.drawable.icon_13};
    private ImageView image_view_question;
    private int currentIndex = 0;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image_view_question = findViewById(R.id.image_view_question);
        showImage();


    }

    private void showImage() {

        SharedPreferences preferences = getSharedPreferences(Constant.KeyPref, Context.MODE_PRIVATE);
        int save_current_index = preferences.getInt("save_current_index", 0);
        Drawable drawable = getResources().getDrawable(heritage_Image[save_current_index]);
        image_view_question.setImageDrawable(drawable);

    }

    public void image_share_question(View view) {
        SharedPreferences preferences = getSharedPreferences(Constant.KeyPref, Context.MODE_PRIVATE);
        int save_current_index = preferences.getInt("save_current_index", 0);
        Intent intent = new Intent(getApplicationContext(), shareActivity.class);
        intent.putExtra("image_share_question", heritage_Image[save_current_index]);
        startActivity(intent);

    }

    public void button_change_question(View view) {
        random = new Random();
        currentIndex = random.nextInt(heritage_Image.length);
        saveCurrentIndex();
        showImage();
    }


    public void button_open_answer(View view) {
        Intent open_answer = new Intent(MainActivity.this, AnswerActivity.class);
        open_answer.putExtra("currentIndexAnswer", currentIndex);
        startActivity(open_answer);
    }

    public void button_change_language(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_help_white_24dp)
                .setTitle(R.string.titleDailog)
                .setItems(R.array.language_item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int wish) {
                        String language = "ar";

                        switch (wish) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;

                        }

                        LocaleHelper.setLocale(getApplicationContext(), language);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                    }
                });
        dialog.create();
        dialog.show();


    }

    public void saveCurrentIndex() {
        SharedPreferences preferences = getSharedPreferences(Constant.KeyPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("save_current_index", currentIndex);
        editor.apply();

    }
}
