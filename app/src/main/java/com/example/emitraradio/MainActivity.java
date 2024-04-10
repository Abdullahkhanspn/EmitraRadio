package com.example.emitraradio;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private ImageView playButton;
    private boolean isPlaying = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize MediaPlayer with your sound file
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);

        // Find the ImageView
        playButton = findViewById(R.id.playButton);

        // Set click listener to ImageView
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // Pause the sound and animation
                    pauseSound();
                } else {
                    // Start playing the sound and animation
                    playSound();
                }
            }
        });
    }

    // Method to play sound and animation
    private void playSound() {
        // Start the sound
        mediaPlayer.start();

        // Load GIF with Glide
        Glide.with(this).asGif().load(R.drawable.main).into(playButton);

        isPlaying = true;
    }

    // Method to pause sound and animation
    private void pauseSound() {
        // Pause the sound
        mediaPlayer.pause();

        // Set the play icon
        playButton.setImageResource(R.drawable.main);

        isPlaying = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release MediaPlayer when activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
