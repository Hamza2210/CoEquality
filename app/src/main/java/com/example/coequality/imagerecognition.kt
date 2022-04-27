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

    //initialising all variables of UI elements and tts to be referenced and edited when needed
    lateinit var galleryButton: ImageButton
    lateinit var analyseButton: Button
    lateinit var imageDisplay: ImageView
    lateinit var txtResult: TextView
    lateinit var bitmap: Bitmap
    lateinit var openCamera: ImageButton
    var tts: TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagerecognition)

        //method used to call the tts on create and uses the google tts engine
        tts = TextToSpeech(
            applicationContext,
            { speakOut() }, "com.google.android.tts"
        )

        //assigning all UI elements to the variables to be referenced when neeeded
        galleryButton = findViewById(R.id.button)
        analyseButton = findViewById(R.id.button2)
        imageDisplay = findViewById(R.id.imageView2)
        txtResult = findViewById(R.id.imageResult)
        openCamera = findViewById(R.id.camerabtn)

    }


    fun makePrediction(view: View){

        //if statement used to check if an image is currently within the image view
        //if no image is in the image view, a textual and auditory message prompts the user to select the image
        if(imageDisplay.drawable == null){
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
            tts!!.speak("Please select an image", TextToSpeech.QUEUE_FLUSH, null, "")
        }

        //opens the assets file of 1000 categories and reads all categories whilst accoutning for new line seperators
        else {
            val labels =
                application.assets.open("labels.txt").bufferedReader().use { it.readText() }
                    .split("\n")

            //resizes bitmap in order to accurately display the chosen bitmap (224x224 is the optimal size)
            val resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true)

            //initialises AI model used to recognise an image
            val model = MobilenetV110224Quant.newInstance(this)

            //creates tensorimage from resized bitmap to be analysed by AI model
            val tBuffer = TensorImage.fromBitmap(resized)
            val byteBuffer = tBuffer.buffer

            // Creates inputs for reference.
            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.UINT8)
            inputFeature0.loadBuffer(byteBuffer)
            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            //calls getMax method which iterates through 1000 categories and finds closest category match to image
            val max = getMax(outputFeature0.floatArray)

            //sets textview text to closest image
            txtResult.text = labels[max]

            model.close()
        }


    }

    @Suppress("DEPRECATION")
    //sets request code of 200 and creates intent to direct the user to the device's camera
    fun goToCamera(view: View){
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camera, 200)
    }

    @Suppress("DEPRECATION")
    //sets request code of 250 and directs the user to the device's gallery by declaring specific image intent type
    fun goToGallery(view: View) {
        Log.d("mssg", "button pressed")
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 250)

    }

    @Suppress("DEPRECATION")
    //overriden method used to execute the method based on the request code specified
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //sets the imageview to the chosen gallery image of the request code is 250
        if (requestCode == 250) {
            imageDisplay.setImageURI(data?.data)


            val uri: Uri? = data?.data

            //retrieving the uri image that is stored within the data variable
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)

        //sets the image view to the camera snapped image if request code is 200 and the result is OK (supported by the device)
        } else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
            bitmap = data?.extras?.get("data") as Bitmap
            imageDisplay.setImageBitmap(bitmap)
        }

    }

   //method which iterates through the 1000 categories of assets and returns inference match
   private fun getMax(arr: FloatArray): Int {
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

    //backbutton edited to allow for a fade animation to play when clicking the back button
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }

    //method used to retrieve the text of the textview based on the image present in the imageview and have it spoken out loud when calling this method
    fun imageResultSay(view: View) {
        val text = findViewById<TextView>(R.id.imageResult).text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}