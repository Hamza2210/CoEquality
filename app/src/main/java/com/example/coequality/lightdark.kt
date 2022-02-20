package com.example.coequality

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import com.example.coequality.R
import androidx.appcompat.app.AppCompatDelegate
import android.widget.CompoundButton
import android.widget.ImageView
import java.util.*

class lightdark : AppCompatActivity() {

    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lightdark)

        tts = TextToSpeech(
            applicationContext,
            { speakOut() }, "com.google.android.tts"
        )

        var imageView = findViewById<ImageView>(R.id.imageView)
        var switchCompat = findViewById<SwitchCompat>(R.id.switchCompat)
        var sharedPreferences = getSharedPreferences("night", 0)

        val booleanValue = sharedPreferences.getBoolean("night_mode", true)

        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            switchCompat.isChecked = true
            imageView.setImageResource(R.drawable.night)
        }
        switchCompat.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchCompat.setChecked(true)
                imageView.setImageResource(R.drawable.night)
                val editor = sharedPreferences.edit()
                editor.putBoolean("night_mode", true)
                editor.commit()


            } else if (!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchCompat.setChecked(false)
                imageView.setImageResource(R.drawable.day)
                val editor = sharedPreferences.edit()
                editor.putBoolean("night_mode", false)
                editor.commit()

            }

            recreate()
        }


    }

    fun returnToMenu(view: View){
        val intent = Intent(this, toolselect::class.java)
        startActivity(intent)
    }

    private fun speakOut() {
        val text = "Change Theme"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}