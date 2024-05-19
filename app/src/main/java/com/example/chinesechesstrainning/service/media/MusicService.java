package com.example.chinesechesstrainning.service.media;

import com.example.chinesechesstrainning.R;

public class MusicService extends MediaService {
    @Override
    protected int getAudioResourceId() {
        // Return the resource ID of the music file
        return R.raw.music;
    }

    @Override
    protected boolean isLooping() {
        // Indicate that the music should loop
        return true;
    }
}
