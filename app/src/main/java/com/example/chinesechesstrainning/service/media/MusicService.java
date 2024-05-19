package com.example.chinesechesstrainning.service.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.chinesechesstrainning.R;

public class MusicService extends MediaService {
    private static MusicService instance;

    public static MusicService getInstance() {
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
        return R.raw.music;
    }

    @Override
    protected boolean isLooping() {
        return true;
    }
}
