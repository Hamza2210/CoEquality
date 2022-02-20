package com.example.coequality

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.example.coequality.model.DataBaseHelper
import com.example.coequality.model.Image
import java.util.*
import kotlin.collections.ArrayList

class picturevoice : AppCompatActivity() {

    lateinit var imageList: ArrayList<Image>
    var imageIndex = 0
    var tts: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picturevoice)

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

        val dbHelper = DataBaseHelper(this)
        imageList = dbHelper.getAllImages()

        var myImage: ByteArray = imageList[imageIndex].image
        var bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        findViewById<ImageButton>(R.id.imageButton1).setImageBitmap(bmp)

        myImage = imageList[imageIndex+1].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton2).setImageBitmap(bmp)

        myImage = imageList[imageIndex+2].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton3).setImageBitmap(bmp)

        myImage = imageList[imageIndex+3].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton4).setImageBitmap(bmp)

        myImage = imageList[imageIndex+4].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton5).setImageBitmap(bmp)

        myImage = imageList[imageIndex+5].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton6).setImageBitmap(bmp)

        myImage = imageList[imageIndex+6].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton7).setImageBitmap(bmp)

        myImage = imageList[imageIndex+7].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton8).setImageBitmap(bmp)

        myImage = imageList[imageIndex+8].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton9).setImageBitmap(bmp)

        myImage = imageList[imageIndex+9].image
        bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)
        findViewById<ImageButton>(R.id.imageButton10).setImageBitmap(bmp)


    }

    fun displayAndSay1(view: View){

        val myImage: ByteArray = imageList[imageIndex].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("Eat")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "Eat"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay2(view: View){

        val myImage: ByteArray = imageList[imageIndex+1].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("Car")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "Car"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay3(view: View){

        val myImage: ByteArray = imageList[imageIndex+2].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("House")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "House"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay4(view: View){

        val myImage: ByteArray = imageList[imageIndex+3].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("Family")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "Family"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay5(view: View){

        val myImage: ByteArray = imageList[imageIndex+4].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("Want")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "Want"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay6(view: View){

        val myImage: ByteArray = imageList[imageIndex+5].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("Good")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "Good"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay7(view: View){

        val myImage: ByteArray = imageList[imageIndex+6].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("Yes")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "Yes"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay8(view: View){

        val myImage: ByteArray = imageList[imageIndex+7].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("No")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "No"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay9(view: View){

        val myImage: ByteArray = imageList[imageIndex+8].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("Hello")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "Hello"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }

    fun displayAndSay10(view: View){

        val myImage: ByteArray = imageList[imageIndex+9].image
        val bmp: Bitmap = BitmapFactory.decodeByteArray(myImage, 0, myImage.size)

        var image = ImageView(this)
        image.setImageBitmap(bmp)
        AlertDialog.Builder(this)
            .setTitle("Bye")
            .setPositiveButton(android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.btn_star)
            .setView(image)
            .show()

        val text = "Bye"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")

    }



    fun onInit(status: Int) {

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
        val text = "Picture Voice"
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