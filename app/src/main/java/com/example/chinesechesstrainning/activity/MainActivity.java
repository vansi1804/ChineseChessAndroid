package com.example.chinesechesstrainning.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.adapter.TrainingItemAdapter;
import com.example.chinesechesstrainning.model.training.TrainingDTO;
import com.example.chinesechesstrainning.service.media.MusicService;
import com.example.chinesechesstrainning.support.Support;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imgBtnSpeaker;
    private ImageButton imgBtnMusic;
    private Button btnTraining;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            imgBtnSpeaker.setTag("off");
            imgBtnMusic.setTag("off");
        }

        setImgBtnMusicService(imgBtnMusic.getTag().toString());
        setImgBtnSpeakerService(imgBtnSpeaker.getTag().toString());
    }

    @Override
    public void onClick(View v) {
        if (v == imgBtnSpeaker) {
            setImgBtnSpeakerService(imgBtnSpeaker.getTag().equals("on") ? "off" : "on");
        } else if (v == imgBtnMusic) {
            setImgBtnMusicService(imgBtnMusic.getTag().equals("on") ? "off" : "on");
        } else if (v == btnTraining) {
            Intent intent = new Intent(this, TrainingActivity.class);
            intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
            intent.putExtra("music", imgBtnMusic.getTag().toString());
            startActivity(intent);
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setImgBtnMusicService(String tag) {
        imgBtnMusic.setTag(tag);
        if (imgBtnMusic.getTag().toString().equals("on")) {
            imgBtnMusic.setBackground(getDrawable(R.drawable.music_on));
            if (MusicService.getInstance() == null) {
                startService(new Intent(this, MusicService.class));
            }
        } else {
            imgBtnMusic.setBackground(getDrawable(R.drawable.music_off));
            stopService(new Intent(this, MusicService.class));
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setImgBtnSpeakerService(String tag) {
        imgBtnSpeaker.setTag(tag);
        if (imgBtnSpeaker.getTag().toString().equals("on")) {
            imgBtnSpeaker.setBackground(getDrawable(R.drawable.speaker_on));
        } else {
            imgBtnSpeaker.setBackground(getDrawable(R.drawable.speaker_off));
        }
    }

}