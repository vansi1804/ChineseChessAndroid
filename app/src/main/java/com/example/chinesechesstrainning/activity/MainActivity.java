package com.example.chinesechesstrainning.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.enumerable.MediaStatus;

public class MainActivity extends HeaderActivity {

    private Button btnTraining;
    private Button btnPlayWith2Player;
    private Button btnPlayWithComputer;

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

        btnPlayWith2Player = findViewById(R.id.btn_play_with_2_player);
        btnPlayWith2Player.setOnClickListener(this);

        btnPlayWithComputer = findViewById(R.id.btn_play_with_computer);
        btnPlayWithComputer.setOnClickListener(this);

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
        }else if (v == btnPlayWith2Player){
            playWith2PlayerOnClick();
        }else if (v == btnPlayWithComputer){
            playWithComputerOnClick();
        }
    }

    @Override
    protected void setBackOnClick() {

    }

    private void playWithComputerOnClick() {
    }

    private void playWith2PlayerOnClick() {
        Intent intent = new Intent(this, MatchActivity.class);
        intent.putExtra("source", "MainActivity");
        intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
        intent.putExtra("music", imgBtnMusic.getTag().toString());
        startActivity(intent);
    }

    public void trainingOnClick(){
        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("source", "MainActivity");
        intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
        intent.putExtra("music", imgBtnMusic.getTag().toString());
        intent.putExtra("title", btnTraining.getText().toString());
        startActivity(intent);
    }

}