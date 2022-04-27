package com.example.coequality

import android.content.Context
import android.media.AudioAttributes
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class vibration : AppCompatActivity() {

    var tts: TextToSpeech? = null

    //initalise top level vibration variable to call vibrator system service to use
    @Suppress("DEPRECATION")
    private val vibrator: Vibrator by lazy {
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibration)

        //method used to call the tts on create and uses the google tts engine
        tts = TextToSpeech(applicationContext,
            { speakOut() }, "com.google.android.tts"
        )

    }

    @Suppress("DEPRECATION")
    fun constantVibrate(view: View){
        //ensures to cancel any preexiting vibrations beforehand
        vibrator.cancel()
        //sends a vibration of 2000 milliseconds to the phone when called
        vibrator.vibrate(2000L)
    }

    @Suppress("DEPRECATION")
    fun patternVibration(view: View){
        //establishes pattern of milliseconds of vibrations
        val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)
        //ensures to cancel any preexiting vibrations beforehand
        vibrator.cancel()
        //sends a vibration based on the pattern of milliseconds and declares to not repeat the pattern
        vibrator.vibrate(pattern, -1)
    }

    @Suppress("DEPRECATION")
    fun patternVibrationRepeat(view: View){
        //establishes pattern of milliseconds of vibrations
        val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)
        //ensures to cancel any preexiting vibrations beforehand
        vibrator.cancel()
        //sends a vibration based on the pattern of milliseconds and declares to repeat the pattern
        vibrator.vibrate(pattern, 0)
    }

    fun singleLowAmplitude(view: View){
        //ensures to cancel any preexiting vibrations beforehand
        vibrator.cancel()
        //sends a vibration of 2000 milliseconds with a low level amplitude (1)
        vibrator.vibrate(
            VibrationEffect.createOneShot(2000L, 1)
        )
    }

    fun singleMidAmplitude(view: View){
        //ensures to cancel any preexiting vibrations beforehand
        vibrator.cancel()
        vibrator.vibrate(
            //sends a vibration of 2000 milliseconds with a medium level amplitude (127)
            VibrationEffect.createOneShot(2000L, 127)
        )
    }

    fun singleMaxAmplitude(view: View){
        //ensures to cancel any preexiting vibrations beforehand
        vibrator.cancel()
        //sends a vibration of 2000 milliseconds with maximum level amplitude (255)
        vibrator.vibrate(
            VibrationEffect.createOneShot(2000L, 255)
        )
    }

    fun predefineTick(view: View){
        //ensures the tick vibration effect is supported on the device
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (isEffectSupported(VibrationEffect.EFFECT_TICK)) {
                //ensures to cancel any preexisting vibrations beforehand
                vibrator.cancel()
                //sends a small vibrational tick effect when clicked
                vibrator.vibrate(
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
                )
            //error message to show effect is not supported on the device
            } else {
                makeToast("EFFECT_TICK is not supported")
            }
        //error message to show if the SDK version is not supported
        } else {
            makeToast("Min SDK Version: 29")
        }
    }

    fun predefineClick(view: View){
        //ensures the click vibration effect is supported on the device
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (isEffectSupported(VibrationEffect.EFFECT_CLICK)) {
                //ensures to cancel any preexisting vibrations beforehand
                vibrator.cancel()
                vibrator.vibrate(
                    //sends a small vibrational click effect when clicked
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
                )
            //error message to show effect is not supported on the device
            } else {
                makeToast("EFFECT_CLICK is not supported")
            }
        //error message to show if the SDK version is not supported
        } else {
            makeToast("Min SDK Version: 29")
        }
    }

    fun predefineHeavyClick(view: View){
        //ensures the heavy click vibration effect is supported on the device
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (isEffectSupported(VibrationEffect.EFFECT_HEAVY_CLICK)) {
                //ensures to cancel any preexisting vibrations beforehand
                vibrator.cancel()
                vibrator.vibrate(
                    //sends a small vibrational heavy click effect when clicked
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
                )
            //error message to show effect is not supported on the device
            } else {
                makeToast("EFFECT_HEAVY_CLICK is not supported")
            }
        //error message to show if the SDK version is not supported
        } else {
            makeToast("Min SDK Version: 29")
        }
    }


    fun waveformVibration(view: View){
        //establishes pattern of milliseconds for the vibration pattern
        val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)
        //ensures to cancel any preexisting vibrations beforehand
        vibrator.cancel()
        //sends a waveform unrepeating pattern based on the milliseconds of patterns established
        vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
    }

    fun waveformAmplitudes(view: View){
        //establishes pattern of milliseconds for the vibration pattern
        val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)
        //establishes pattern of amplitude values for the vibration pattern
        val amplitudes = intArrayOf(0, 255, 0, 127, 0, 100, 0, 255, 0)
        //ensures to cancel any preexisting vibrations beforehand
        vibrator.cancel()
        //sends a waveform unrepeating pattern based on the milliseconds of patterns established and amplitudes established
        vibrator.vibrate(VibrationEffect.createWaveform(pattern, amplitudes, -1))
    }

    //method used to check if a ceetain effect is supported on the device
    private fun isEffectSupported(effectId: Int): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            vibrator.areAllEffectsSupported(effectId) == Vibrator.VIBRATION_EFFECT_SUPPORT_YES
        } else {
            // Assume the effect is supported
            true
        }
    }

    //establish toast messages
    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //tts method used to state name of activity on create
    private fun speakOut() {
        val text = "Vibration Tool"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    //stops tts speech if nothing is queued
    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    //back button to allow for fade transition
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        vibrator.cancel()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }


}