package com.example.coequality

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class lightsensor : AppCompatActivity(), SensorEventListener, TextToSpeech.OnInitListener {

    //variables declared to initialise UI elements and sensor managers
    private lateinit var sensorManager: SensorManager
    private var brightness: Sensor? = null
    var image: ImageView? = null
    var bulb: Boolean = true
    var tts: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lightsensor)

        //method used to call the tts on create and uses the google tts engine
        tts = TextToSpeech(
            applicationContext,
            { speakOut() }, "com.google.android.tts"
        )


        //Initalise variable to retrieve sensor
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        //assign variable to select device's light sensor
        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)


        image = findViewById(R.id.bulbImage)

        //call light measure methods in onCreate to activate when entering the activity
        setUpSensorStuff()
    }


    private fun setUpSensorStuff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        //checks if environment's brightness is lower than 20 sensor event values
        if (event!!.values[0] > 20) {
            if (bulb) {
                //sets imageview to off bulb when environment is above 20 sensor event values
                image?.setImageResource(R.drawable.bulboff)
                //dynamically sets the background to dark grey
                findViewById<ConstraintLayout>(R.id.relLayout).setBackgroundColor(Color.DKGRAY)
                bulb = false
            } else {
                return
            }
        } else {
            bulb = true
            //Sets background colour to white if evironment is darker than 20 sensor event values
            findViewById<ConstraintLayout>(R.id.relLayout).setBackgroundColor(Color.WHITE)
            //Sets imageview resource to on light bulb
            image?.setImageResource(R.drawable.bulbon)
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        // Register a listener for the sensor.
        sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL)
    }


    //overriden function used to unregister listener when not in use
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    //overriden method to ensure ts locale is set to default and to see if tts is available and supported
    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set device default language as language for tts
            val result = tts!!.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            } else {
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }

    //method used to declare the speech outspoken on create of the activity
    private fun speakOut() {
        val text = "Light Sensor Tool"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    //stops tts speech when when queue is empty
    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    //adds fade animation to backpress
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }
}