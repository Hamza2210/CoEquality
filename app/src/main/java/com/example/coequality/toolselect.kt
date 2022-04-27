package com.example.coequality

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import com.example.coequality.model.DataBaseHelper
import java.util.*

class toolselect : AppCompatActivity() {

    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_toolselect)

        //variable created to retrieve the shared preference named "night" to retrieve the current light/dark mode value
        var sharedPreferences = getSharedPreferences("night", 0)

        //boolean value assigned to shared preference to check to see if it is active
        val booleanValue = sharedPreferences.getBoolean("night_mode", true)

        //if the shared preference contains dark mode to be true, the background is dynamically set to dark and mode is
        if (booleanValue) {
            findViewById<LinearLayout>(R.id.toolSelect).setBackgroundResource(R.drawable.lightdark_bg)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        //if the shared preference contains dark mode to be `false`, the background is dynamically set to light and mode is
        else{
            findViewById<LinearLayout>(R.id.toolSelect).setBackgroundResource(R.drawable.lightbackground)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun goToLightSensor(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the light view in the view xml
        val lightPane = findViewById<CardView>(R.id.lightCard)
        //applies bounce animation to the light pane in the view
        lightPane.startAnimation(animation)
        //intent to direct user to the lightsensor utility
        val intent = Intent(this, lightsensor::class.java)
        startActivity(intent)
        //fading animation when transitioning
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun goToSpeechText(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the speech view in the view xml
        val speechPane = findViewById<CardView>(R.id.speechCard)
        //applies bounce animation to the speech pane in the view
        speechPane.startAnimation(animation)
        //intent to direct user to the speechtext utility
        val intent = Intent(this, speechtotext::class.java)
        startActivity(intent)
        //fading animation when transitioning
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun onExit(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the exit in the view xml
        val exitPane = findViewById<CardView>(R.id.exitCard)
        //applies bounce animation to the exit pane in the view
        exitPane.startAnimation(animation)
        //closes all activities and application
        this.finishAffinity()
    }

    fun goToVibrate(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the vibration tool in the view xml
        val vibratePane = findViewById<CardView>(R.id.vibrationCard)
        //applies bounce animation to the vibration tool pane in the view
        vibratePane.startAnimation(animation)
        //intent to direct user to the vibration tool utility
        val intent = Intent(this, vibration::class.java)
        startActivity(intent)
        //fading animation when transitioning
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun goToPictureVoice(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the picture voice in the view xml
        val picturePane = findViewById<CardView>(R.id.pictureCard)
        //applies bounce animation to the picture voice pane in the view
        picturePane.startAnimation(animation)
        //intent to direct user to the picture voice utility
        val intent = Intent(this, picturevoice::class.java)
        startActivity(intent)
        //fading animation when transitioning
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun goToImageRecognition(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the image recognition in the view xml
        val recogniserPane = findViewById<CardView>(R.id.recogniserCard)
        //applies bounce animation to the image recognition pane in the view
        recogniserPane.startAnimation(animation)
        //intent to direct user to the picture voice utility
        val intent = Intent(this, imagerecognition::class.java)
        startActivity(intent)
        //fading animation when transitioning
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun chooseTheme(view: View){
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the choose theme in the view xml
        val themePane = findViewById<CardView>(R.id.themeCard)
        //applies bounce animation to the choose theme pane in the view
        themePane.startAnimation(animation)
        //intent to direct user to the choose theme activity
        val intent = Intent(this, lightdark::class.java)
        startActivity(intent)
        //fading animation when transitioning
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun aboutDialog(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the about dialog in the view xml
        val aboutPane = findViewById<CardView>(R.id.aboutCard)
        //applies bounce animation to the about pane in the view
        aboutPane.startAnimation(animation)
        //creates alert dialog with the message regarding the application
        AlertDialog.Builder(this)
            .setTitle("CoEquality")
            .setMessage(R.string.aboutus)
            .setPositiveButton(
                android.R.string.ok
            ) { _, _ ->
                // Continue with delete operation
            } // A null listener allows the button to dismiss the dialog and take no further action.
            .setIcon(android.R.drawable.ic_dialog_info)
            .show()
    }

    fun resetApp(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the reset app in the view xml
        val resetPane = findViewById<CardView>(R.id.resetCard)
        //applies bounce animation to the reset app pane in the view
        resetPane.startAnimation(animation)
        //creates alert dialog box asking to confirm the currently set password
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.confirm_password)

        val confirmPassword = EditText(this)

        confirmPassword.setHint("Enter Text")
        //ensures only a number can be entered as the passcode to confirm
        confirmPassword.inputType = InputType.TYPE_CLASS_NUMBER
        builder.setView(confirmPassword)

        builder.setPositiveButton("OK") { dialog, which ->
            var passWord = confirmPassword.text.toString()
            //checks to see if the password entered matches what currently resides in the table
            if (passWord == dbHelper.getPasscode().last().passcode) {
                //alls delete method to clear the table for a new passcode to be entered
                dbHelper.deleteAll()
                //intent to direct the user back to the passcode entry screne to enter a new passcode
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
              //else displays error message informing the passcode entered is incorrect
            } else {
                Toast.makeText(this, "Incorrect Password Entered", Toast.LENGTH_SHORT).show()
            }
        }
        //cancel dialog box if cancel is applied and nothing happens
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        confirmPassword.gravity = Gravity.CENTER

        builder.show()
    }

    fun showLanguageDialog(view: View) {
        //initialises variable by calling bounce animation xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        //finds pane for the languages in the view xml
        val languagePane = findViewById<CardView>(R.id.languageCard)
        //applies bounce animation to the language pane in the view
        languagePane.startAnimation(animation)
        //establish list of languages to be displayed in the alert dialog
        val listItems = arrayOf("French", "German", "Italian", "English")
        //alert dialog box is created with a list of languages established
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(R.string.choose_language)
        //ensures that 1 item can be selected at a time and makes sure that -1 means no item is selected
        builder.setSingleChoiceItems(listItems, -1) { dialog, i ->
            //if the first option (french) is chosen, change all strings to fr locale and refresh the activity
            if (i == 0) {
                //French
                setLocale("fr")
                recreate()
            //if the second option (German) is chosen, change all strings to de region locale and refresh the activity
            } else if (i == 1) {
                //German
                setLocale("de")
                recreate()
            //if the third option (Italian) is chosen, change all strings to it region locale and refresh the activity
            } else if (i == 2) {
                //Italian
                setLocale("it")
                recreate()
            //if the fourth option (English) is chosen, change all strings to en region locale and refresh the activity
            } else if (i == 3) {
                //English
                setLocale("en")
                recreate()
            }
            //dismiss the dialog box once an option is selected
            dialog.dismiss()
        }

        //show the dialog box appear in the application
        val languageChoose = builder.create()
        languageChoose.show()
    }

    @Suppress("DEPRECATION")
    //methdo which takes a string parameter to change the app's locale based on the string region code entered
    private fun setLocale(lang: String?) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        //updates the aps configuration to the chosen locale when selected
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        //adds the set locale to shared preferences to load language when returning to the activity
        val editor = getSharedPreferences("Settings", MODE_PRIVATE).edit()
        editor.putString("My_Lang", lang)
        editor.apply()
    }

    //method used to call the shared preferences and load the last locale written to the shared preferences
    fun loadLocale() {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val language = prefs.getString("My_Lang", "")
        setLocale(language)
    }

    //override back button to allow for fade transition
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, mainmenu::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }


}