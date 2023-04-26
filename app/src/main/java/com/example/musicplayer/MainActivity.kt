package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
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

            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()
            }
        }

//         when user changes time stamp of music reflect that changes
        binding.seekBarMusic.max = totalTime

        binding.seekBarMusic.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d("SeekBar", "User started touching the SeekBar")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d("SeekBar", "User stopped touching the SeekBar")
            }


        })

//        change the seekBar position based on music

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBarMusic.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)

                } catch (e: Exception) {
                    binding.seekBarMusic.progress = 0
                    Log.d("Exaption", "${e.message}")
                }

            }


        }, 0)

    }
}