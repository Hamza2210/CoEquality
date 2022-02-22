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

        var sharedPreferences = getSharedPreferences("night", 0)

        val booleanValue = sharedPreferences.getBoolean("night_mode", true)

        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        tts = TextToSpeech(applicationContext,
            { speakOut() }, "com.google.android.tts"
        )

    }

    fun speakIntoMic(view: View){
        askSpeechInput()
    }

    @SuppressWarnings("deprecation")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //If the request code for the mic matches the initialised one, create a string array to pick yp the words from the mic
        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result: ArrayList<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            //display spoken text into the text view
            findViewById<TextView>(output).text = result?.get(0).toString()
        }
    }

    @SuppressWarnings("deprecation")
    private fun askSpeechInput(){
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

    fun returnToMenu(view: View){
        val intent = Intent(this, toolselect::class.java)
        startActivity(intent)
    }

    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }

    private fun speakOut() {
        val text = "Speech To Text Tool"
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