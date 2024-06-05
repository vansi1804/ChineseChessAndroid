package com.example.chinesechesstrainning.service.media;

import com.example.chinesechesstrainning.R;

import lombok.Getter;

public class MusicService extends MediaService {
    @Getter
    private static MusicService instance;

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
