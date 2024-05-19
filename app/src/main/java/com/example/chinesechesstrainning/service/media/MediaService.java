package com.example.chinesechesstrainning.service.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

public abstract class MediaService extends Service {
    protected MediaPlayer mediaPlayer; // MediaPlayer instance to play the audio
    private AudioManager audioManager; // AudioManager to manage volume

    @Override
    public IBinder onBind(Intent intent) {
        // Service binding is not supported in this implementation
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Get the AudioManager system service
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Set the device's media volume to the maximum
        setMaxVolume();

        // Initialize the MediaPlayer with the appropriate audio resource
        mediaPlayer = MediaPlayer.create(this, getAudioResourceId());

        // Set the volume of the MediaPlayer to maximum for both left and right channels
        mediaPlayer.setVolume(1.0f, 1.0f);

        // Enable looping if needed
        mediaPlayer.setLooping(isLooping());

        // Start playing the audio
        mediaPlayer.start();

        // Use START_STICKY to ensure the service restarts if it's killed by the system
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the MediaPlayer and release its resources
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Method to set the device's media volume to maximum
    private void setMaxVolume() {
        if (audioManager != null) {
            // Get the maximum volume level for the media stream
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            // Set the device's media volume to the maximum level
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        }
    }

    // Abstract method to be implemented by subclasses to provide the audio resource ID
    protected abstract int getAudioResourceId();

    // Abstract method to be implemented by subclasses to specify if the audio should loop
    protected abstract boolean isLooping();
}
