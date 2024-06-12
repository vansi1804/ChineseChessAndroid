package com.example.chinesechesstrainning.service.media.music;

import android.util.Log;

import com.example.chinesechesstrainning.R;
import com.example.chinesechesstrainning.service.media.MediaService;

import lombok.Getter;

public class MusicService extends MediaService {
    @Getter
    private static MusicService instance;
    private static final String TAG = "MusicService";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.d(TAG, "created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        Log.d(TAG, "destroyed");
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
