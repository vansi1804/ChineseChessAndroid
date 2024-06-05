package com.example.chinesechesstrainning.service.media;

import com.example.chinesechesstrainning.R;

public class SpeakerService extends MediaService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
