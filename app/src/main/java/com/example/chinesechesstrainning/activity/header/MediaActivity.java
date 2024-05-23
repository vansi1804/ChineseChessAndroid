package com.example.chinesechesstrainning.activity.header;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.enumerable.MediaStatus;
import com.example.chinesechesstrainning.service.media.MusicService;

public abstract class MediaActivity extends AppCompatActivity implements View.OnClickListener {

    protected ImageButton imgBtnSpeaker;
    protected ImageButton imgBtnMusic;

    @Override
    public void onClick(View v) {
        if (v == imgBtnSpeaker) {
           speakerOnClick();
        } else if (v == imgBtnMusic) {
            musicOnClick();
        }
    }
    
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void speakerOnClick(){
        if(MediaStatus.ON.equals(imgBtnSpeaker.getTag())){
            imgBtnSpeaker.setBackground(getDrawable(R.drawable.speaker_off));
            imgBtnSpeaker.setTag(MediaStatus.OFF);
        }else {
            imgBtnSpeaker.setBackground(getDrawable(R.drawable.speaker_on));
            imgBtnSpeaker.setTag(MediaStatus.ON);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    protected void musicOnClick(){
        if(MediaStatus.ON.equals(imgBtnMusic.getTag())){
            imgBtnMusic.setBackground(getDrawable(R.drawable.music_off));
            stopService(new Intent(this, MusicService.class));
            imgBtnMusic.setTag(MediaStatus.OFF);
        }else {
            imgBtnMusic.setBackground(getDrawable(R.drawable.music_on));
            if (MusicService.getInstance() == null) {
                startService(new Intent(this, MusicService.class));
            }
            imgBtnMusic.setTag(MediaStatus.ON);
        }
    }
}
