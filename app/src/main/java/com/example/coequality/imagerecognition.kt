package com.example.coequality

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.coequality.ml.MobilenetV110224Quant
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.util.*

class imagerecognition : AppCompatActivity() {

    lateinit var galleryButton: ImageButton
    lateinit var analyseButton: Button
    lateinit var imageDisplay: ImageView
    lateinit var txtResult: TextView
    lateinit var bitmap: Bitmap
    lateinit var openCamera: ImageButton
    var tts: TextToSpeech? = null

    fun checkAndRetrievePermissions() {
        if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            requestPermissions(arrayOf(android.Manifest.permission.CAMERA), 100)
        } else {
            Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagerecognition)

        var sharedPreferences = getSharedPreferences("night", 0)

        val booleanValue = sharedPreferences.getBoolean("night_mode", true)

        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        tts = TextToSpeech(
            applicationContext,
            { speakOut() }, "com.google.android.tts"
        )

        galleryButton = findViewById(R.id.button)
        analyseButton = findViewById(R.id.button2)
        imageDisplay = findViewById(R.id.imageView2)
        txtResult = findViewById(R.id.imageResult)
        openCamera = findViewById(R.id.camerabtn)

        // handling permissions
        checkAndRetrievePermissions()
    }

    fun makePrediction(view: View){

        val labels =
            application.assets.open("labels.txt").bufferedReader().use { it.readText() }.split("\n")
        val resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
        val model = MobilenetV110224Quant.newInstance(this)

        val tBuffer = TensorImage.fromBitmap(resized)
        val byteBuffer = tBuffer.buffer

        // Creates inputs for reference.
        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        val max = getMax(outputFeature0.floatArray)

        txtResult.text = labels[max]

        // Releases model resources if no longer used.
        model.close()

    }

    fun goToCamera(view: View){
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, 200)
    }

    fun goToGallery(view: View) {
        Log.d("mssg", "button pressed")
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        startActivityForResult(intent, 250)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 250) {
            imageDisplay.setImageURI(data?.data)

            val uri: Uri? = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            bitmap = data?.extras?.get("data") as Bitmap
            imageDisplay.setImageBitmap(bitmap)
        }

    }

    fun getMax(arr: FloatArray): Int {
        var ind = 0
        var min = 0.0f

        for (i in 0..1000) {
            if (arr[i] > min) {
                min = arr[i]
                ind = i
            }
        }
        return ind
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
        val text = "Image Recognition"
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }

    fun imageResultSay(view: View) {
        val text = findViewById<TextView>(R.id.imageResult).text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}