package com.example.coequality

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.coequality.R.id.output
import java.util.*

class speechtotext : AppCompatActivity(),TextToSpeech.OnInitListener {

    //request code used to enable speech recognition initialised at 102
    private val RQ_SPEECH_REC = 102
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speechtotext)

        //method used to call the tts on create and uses the google tts engine
        tts = TextToSpeech(applicationContext,
            { speakOut() }, "com.google.android.tts"
        )

    }


    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //If the request code for the mic matches the initialised one, create a string array to pick yp the words from the mic
        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result: ArrayList<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            //display spoken text into the text view
            findViewById<TextView>(output).text = result?.get(0).toString()
        }
    }

    @Suppress("DEPRECATION")
    fun askSpeechInput(view: View){
        //Ensures the device has speech recognition capabilities
        if(!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this, "Unable to recognise speech", Toast.LENGTH_SHORT).show()
        }else{
            //creates intent with dialogue box which sets the language and prompts the user to speak into the mic
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak into the Mic!")
            startActivityForResult(i, RQ_SPEECH_REC)
        }
    }

    //applied to menu button to allow for returning to the tool select menu
    fun returnToMenu(view: View){
        val intent = Intent(this, toolselect::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    //applied to the clear button which allows for any pre-existing text to be cleared
    fun clear(view: View){
        findViewById<TextView>(output).text = " "
    }

    //overridden method to initialise the locale of the tts and to ensure tts is supported
    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }

    }

    //tts voice declaring the activity name when entering the activity (called in OnCreate)
    private fun speakOut() {
        val text = "Speech To Text Tool"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    //overriden method ensuring to stop tts when no text is queued
    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    //backpress method to allow for fade animation to be added
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }


}