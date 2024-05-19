package com.example.chinesechesstrainning.service.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.chinesechesstrainning.R;

public class SpeakerService extends MediaService {
    private static SpeakerService instance;

    public static SpeakerService getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    @Override
    protected int getAudioResourceId() {
        return R.raw.speaker;
    }

    @Override
    protected boolean isLooping() {
        return false;
    }
}
