package com.example.coequality

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class toolselect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolselect)
    }

    fun goToLightSensor(view: View){
        val intent = Intent(this, lightsensor::class.java)
        startActivity(intent)
    }

    fun goToSpeechText(view: View){
        val intent = Intent(this, speechtotext::class.java)
        startActivity(intent)
    }

    fun onExit(view: View){
        this.finishAffinity()
    }

    fun goToVibrate(view: View){
        val intent = Intent(this, vibration::class.java)
        startActivity(intent)
    }

    fun goToPictureVoice(view: View){
        val intent = Intent(this, picturevoice::class.java)
        startActivity(intent)
    }

    fun goToImageRecognition(view: View){
        val intent = Intent(this, imagerecognition::class.java)
        startActivity(intent)
    }

    fun aboutDialog(view: View){
        AlertDialog.Builder(this)
            .setTitle("CoEquality")
            .setMessage(R.string.aboutus)
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.ic_dialog_info)
            .show()
    }
}