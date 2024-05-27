package com.example.chinesechesstrainning.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.activity.header.HeaderActivity;
import com.example.chinesechesstrainning.enumerable.MediaStatus;

public class MainActivity extends HeaderActivity {

    private Button btnTraining;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtnHome = findViewById(R.id.img_btn_home);
        imgBtnHome.setVisibility(View.INVISIBLE);

        imgBtnBack = findViewById(R.id.img_btn_back);
        imgBtnBack.setVisibility(View.INVISIBLE);

        imgBtnSpeaker = findViewById(R.id.img_btn_speaker);
        imgBtnSpeaker.setOnClickListener(this);

        imgBtnMusic = findViewById(R.id.img_btn_music);
        imgBtnMusic.setOnClickListener(this);

        btnTraining = findViewById(R.id.btn_training);
        btnTraining.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            imgBtnSpeaker.setTag(getIntent().getExtras().getString("speaker"));
            imgBtnMusic.setTag(getIntent().getExtras().getString("music"));

            getIntent().getExtras().clear();
        } else {
            imgBtnSpeaker.setTag(MediaStatus.OFF.name());
            imgBtnMusic.setTag(MediaStatus.OFF.name());
        }

        setSpeaker(MediaStatus.valueOf(imgBtnSpeaker.getTag().toString()));
        setMusic(MediaStatus.valueOf(imgBtnMusic.getTag().toString()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == btnTraining) {
            trainingOnClick();
        }
    }

    @Override
    protected void setBackOnClick() {

    }

    public void trainingOnClick(){
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
        intent.putExtra("music", imgBtnMusic.getTag().toString());
        startActivity(intent);
    }

}