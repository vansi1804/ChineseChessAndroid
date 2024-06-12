package com.example.chinesechesstrainning.service.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public abstract class MediaService extends Service {
    protected MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private static final String TAG = "MediaService";

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Log.d(TAG, "created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "started");
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
        Log.d(TAG, "destroyed");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Method to set the device's media volume to maximum
    private void setMaxVolume() {
        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        }
    }

    protected abstract int getAudioResourceId();

    protected abstract boolean isLooping();
}
