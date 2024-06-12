package com.example.chinesechesstrainning.service.media.Speaker;

import com.example.chinesechesstrainning.service.media.MediaService;

public abstract class SpeakerService extends MediaService {

    @Override
    protected boolean isLooping() {
        return false;
    }

    protected abstract int getAudioResourceId();
}
