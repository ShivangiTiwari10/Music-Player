package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    var totalTime: Int = 0

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.setVolume(1f, 1f)
        totalTime = mediaPlayer.duration


        binding.play.setOnClickListener {
            mediaPlayer.start()
            Log.d("MusicS", "${mediaPlayer.start()}")

        }

        binding.pause.setOnClickListener {
            mediaPlayer.pause()
            Log.d("MusicP", "${mediaPlayer.pause()}")

        }
        binding.stop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()

            Log.d("MusicStop", "$mediaPlayer")
        }
    }

}