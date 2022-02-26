package com.example.coequality

import android.content.Context
import android.media.AudioAttributes
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class vibration : AppCompatActivity() {


    @Suppress("DEPRECATION")
    private val vibrator: Vibrator by lazy {
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibration)

    }

    @Suppress("DEPRECATION")
    fun constantVibrate(view: View){
        vibrator.cancel()
        vibrator.vibrate(2000L)
    }

    @Suppress("DEPRECATION")
    fun patternVibration(view: View){
        val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)
        vibrator.cancel()
        vibrator.vibrate(pattern, -1)
    }

    @Suppress("DEPRECATION")
    fun patternVibrationRepeat(view: View){
        val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)
        vibrator.cancel()
        vibrator.vibrate(pattern, 0)
    }

    fun singleVibrate(view: View){
        vibrator.cancel()
        vibrator.vibrate(
            VibrationEffect.createOneShot(2000L, VibrationEffect.DEFAULT_AMPLITUDE)
        )
    }

    fun singleLowAmplitude(view: View){
        vibrator.cancel()
        vibrator.vibrate(
            VibrationEffect.createOneShot(2000L, 1)
        )
    }

    fun singleMidAmplitude(view: View){
        vibrator.cancel()
        vibrator.vibrate(
            VibrationEffect.createOneShot(2000L, 127)
        )
    }

    fun singleMaxAmplitude(view: View){
        vibrator.cancel()
        vibrator.vibrate(
            VibrationEffect.createOneShot(2000L, 255)
        )
    }

    fun predefineTick(view: View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (isEffectSupported(VibrationEffect.EFFECT_TICK)) {
                vibrator.cancel()
                vibrator.vibrate(
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK)
                )
            } else {
                makeToast("EFFECT_TICK is not supported")
            }
        } else {
            makeToast("Min SDK Version: 29")
        }
    }

    fun predefineClick(view: View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (isEffectSupported(VibrationEffect.EFFECT_CLICK)) {
                vibrator.cancel()
                vibrator.vibrate(
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
                )
            } else {
                makeToast("EFFECT_CLICK is not supported")
            }
        } else {
            makeToast("Min SDK Version: 29")
        }
    }

    fun predefineHeavyClick(view: View){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (isEffectSupported(VibrationEffect.EFFECT_HEAVY_CLICK)) {
                vibrator.cancel()
                vibrator.vibrate(
                    VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK)
                )
            } else {
                makeToast("EFFECT_HEAVY_CLICK is not supported")
            }
        } else {
            makeToast("Min SDK Version: 29")
        }
    }

    // C.3. Waveform Vibration
    fun waveformVibration(view: View){
        val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)
        vibrator.cancel()
        vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1))
    }

    fun waveformAmplitudes(view: View){
        val pattern = longArrayOf(0, 100, 1000, 300, 200, 100, 500, 200, 100)
        val amplitudes = intArrayOf(0, 255, 0, 127, 0, 100, 0, 255, 0)
        vibrator.cancel()
        vibrator.vibrate(VibrationEffect.createWaveform(pattern, amplitudes, -1))
    }

    private fun isEffectSupported(effectId: Int): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            vibrator.areAllEffectsSupported(effectId) == Vibrator.VIBRATION_EFFECT_SUPPORT_YES
        } else {
            // Assume the effect is supported
            true
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }


}