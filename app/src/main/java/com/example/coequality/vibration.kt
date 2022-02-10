package com.example.coequality

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coequality.R
import android.os.Vibrator
import android.os.VibrationEffect
import android.view.View
import android.widget.Button

class vibration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // register the button with the appropriate ID
        var bComposeVibration = findViewById<Button>(R.id.makeVibrationCompositionButton)

        // create instance of the vibrator and initialise it with Vibrator system service
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        // handle compose vibration button
        bComposeVibration.setOnClickListener {
            // Note: The first element needs to be 0
            val vibrationWaveFormDurationPattern =
                longArrayOf(0, 10, 200, 500, 700, 1000, 300, 200, 50, 10)

            // the vibration of the type custom waveforms needs the API version 26


                // create VibrationEffect instance and createWaveform of vibrationWaveFormDurationPattern
                // -1 here is the parameter which indicates that the vibration shouldn't be repeated.
                val vibrationEffect =
                    VibrationEffect.createWaveform(vibrationWaveFormDurationPattern, -1)

                // it is safe to cancel all the vibration taking place currently
                vibrator.cancel()

                // now initiate the vibration of the device
                vibrator.vibrate(vibrationEffect)
            }
        }

}