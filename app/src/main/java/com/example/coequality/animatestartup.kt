package com.example.coequality

import androidx.appcompat.app.AppCompatActivity
import android.view.animation.Animation
import android.os.Bundle
import com.example.coequality.R
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.coequality.mainmenu
import java.util.*

class animatestartup : AppCompatActivity(), OnInitListener {
    var zoom: Animation? = null
    var img: ImageView? = null
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tts = TextToSpeech(applicationContext,
            { speakOut() }, "com.google.android.tts"
        )
        setContentView(R.layout.activity_animatestartup)
        zoom = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom)
        img = findViewById(R.id.image)

        img?.startAnimation(zoom)


        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(applicationContext, mainmenu::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            finish()
        }, 2500)
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.UK)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }

    private fun speakOut() {
        val text = "Hello and Welcome! Please select the start button to begin!"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}