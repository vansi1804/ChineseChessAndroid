package com.example.chinesechesstrainning.service.media;

import com.example.chinesechesstrainning.R;

public class SpeakerService extends MediaService {
    @Override
    protected int getAudioResourceId() {
        return R.raw.speaker;
    }

    @Override
    protected boolean isLooping() {
        return false;
    }
}
