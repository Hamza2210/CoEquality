package com.example.coequality

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class animatestartup : AppCompatActivity(), OnInitListener {
    var zoom: Animation? = null
    var img: ImageView? = null
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hides device UI elements to ensure the animation displays in fullscreen for the user
        var newUiOptions: Int = window.decorView.systemUiVisibility
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_FULLSCREEN
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE
        newUiOptions = newUiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = newUiOptions


        //text to speech execution using google android
        tts = TextToSpeech(applicationContext,
            { speakOut() }, "com.google.android.tts"
        )
        setContentView(R.layout.activity_animatestartup)

        //assigns xml animation of heart to zoom variable of type animation
        zoom = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom)

        //finds heart vector that animation consists of
        img = findViewById(R.id.image)

        //start heart animation of zoom
        img?.startAnimation(zoom)


        //created handler to start intent to main menu whilst delaying animation by 2500 milliseconds so animation can play
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(applicationContext, mainmenu::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            finish()
        }, 2500)
    }

    //overriden method to ensure that the locale of the tts is UK and that it is supported in the device
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

    //method to declare what speech is being said in the tts
    private fun speakOut() {
        val text = "Hello and Welcome!"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    //method to ensure that the tts stops when no speech is queued
    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}