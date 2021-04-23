package com.barmej.culturalwords;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Locale;
import java.util.Random;

import static android.view.View.LAYOUT_DIRECTION_LTR;

public class MainActivity extends AppCompatActivity {
    int[] heritage_Image = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3,
            R.drawable.icon_4, R.drawable.icon_5, R.drawable.icon_6, R.drawable.icon_7,
            R.drawable.icon_8, R.drawable.icon_9, R.drawable.icon_10, R.drawable.icon_11,
            R.drawable.icon_12, R.drawable.icon_13};
    private ImageView image_view_question;
    private int currentIndex = 0;
    private Random random;
    private String language = "ar";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        setContentView(R.layout.activity_main);
        image_view_question = findViewById(R.id.image_view_question);
        showImage();


    }


    private void showImage() {
        SharedPreferences preferences = getSharedPreferences(Constant.keyPref, Context.MODE_PRIVATE);
        int save_current_index = preferences.getInt(Constant.keyIndex, 0);
        Drawable drawable = getResources().getDrawable(heritage_Image[save_current_index]);
        image_view_question.setImageDrawable(drawable);

    }

    public void imageShareQuestion(View view) {
        SharedPreferences preferences = getSharedPreferences(Constant.keyPref, Context.MODE_PRIVATE);
        int save_current_index = preferences.getInt(Constant.keyIndex, 0);
        Intent intent = new Intent(getApplicationContext(), ShareActivity.class);
        intent.putExtra(Constant.image_Share_Question, heritage_Image[save_current_index]);
        startActivity(intent);

    }

    public void buttonChangeQuestion(View view) {
        random = new Random();
        currentIndex = random.nextInt(heritage_Image.length);
        saveCurrentIndex();
        showImage();
    }


    public void buttonOpenAnswer(View view) {
        Intent open_answer = new Intent(MainActivity.this, AnswerActivity.class);
        open_answer.putExtra(Constant.keyIndexAnswer, currentIndex);
        startActivity(open_answer);
    }

    public void buttonChangeLanguage(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_language_orange)
                .setTitle(R.string.titleDailog)
                .setItems(R.array.language_item, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(DialogInterface dialogInterface, int wish) {
                        switch (wish) {
                            case 0:
                                language = "ar";

                                break;
                            case 1:
                                language = "en";

                                break;

                        }
                        changeLanguage(language);
                        recreate();


                    }
                });


        dialog.create();
        dialog.show();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loadLanguage() {

        SharedPreferences preferences = getSharedPreferences(Constant.keyPref, Context.MODE_PRIVATE);
        String save_language = preferences.getString(Constant.keyLangSave, "");
        changeLanguage(save_language);


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeLanguage(String language) {
        saveLanguage(language);
        LocaleHelper.setLocale(MainActivity.this, language);

    }


    private void saveCurrentIndex() {
        SharedPreferences preferences = getSharedPreferences(Constant.keyPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Constant.keyIndex, currentIndex);
        editor.apply();

    }

    private void saveLanguage(String language) {
        SharedPreferences preferences = getSharedPreferences(Constant.keyPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.keyLangSave, language);
        editor.apply();

    }


}
