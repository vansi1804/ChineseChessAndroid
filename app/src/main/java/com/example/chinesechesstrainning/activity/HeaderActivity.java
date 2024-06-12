package com.example.chinesechesstrainning.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.enumerable.MediaStatus;
import com.example.chinesechesstrainning.service.media.music.MusicService;

public abstract class HeaderActivity extends AppCompatActivity implements View.OnClickListener {

    protected ImageButton imgBtnHome;
    protected ImageButton imgBtnBack;
    protected ImageButton imgBtnSpeaker;
    protected ImageButton imgBtnMusic;

    @Override
    public void onClick(View v) {
        if (v == imgBtnHome) {
            setHomeOnClick();
        } else if (v == imgBtnBack) {
            setBackOnClick();
        } else if (v == imgBtnSpeaker) {
            setSpeaker(MediaStatus.ON.equals(imgBtnSpeaker.getTag()) ? MediaStatus.OFF : MediaStatus.ON);
        } else if (v == imgBtnMusic) {
            setMusic(MediaStatus.ON.equals(imgBtnMusic.getTag()) ? MediaStatus.OFF : MediaStatus.ON);
        }
    }

    protected void setHomeOnClick() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("speaker", imgBtnSpeaker.getTag().toString());
        intent.putExtra("music", imgBtnMusic.getTag().toString());
        startActivity(intent);
    }

    protected abstract void setBackOnClick();

    protected void setSpeaker(MediaStatus mediaStatus) {
        if (MediaStatus.ON == mediaStatus) {
            imgBtnSpeaker.setBackgroundResource(R.drawable.speaker_on);
            imgBtnSpeaker.setTag(MediaStatus.ON);
        } else {
            imgBtnSpeaker.setBackgroundResource(R.drawable.speaker_off);
            imgBtnSpeaker.setTag(MediaStatus.OFF);
        }
    }

    protected void setMusic(MediaStatus mediaStatus) {
        if (MediaStatus.ON == mediaStatus) {
            imgBtnMusic.setBackgroundResource(R.drawable.music_on);
            if (MusicService.getInstance() == null) {
                startService(new Intent(this, MusicService.class));
            }
            imgBtnMusic.setTag(MediaStatus.ON);
        } else {
            imgBtnMusic.setBackgroundResource(R.drawable.music_off);
            stopService(new Intent(this, MusicService.class));
            imgBtnMusic.setTag(MediaStatus.OFF);
        }
    }


}
