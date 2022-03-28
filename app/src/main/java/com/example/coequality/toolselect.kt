package com.example.coequality

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.coequality.model.DataBaseHelper
import java.util.*

class toolselect : AppCompatActivity() {

    val dbHelper: DataBaseHelper = DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_toolselect)

        var sharedPreferences = getSharedPreferences("night", 0)

        val booleanValue = sharedPreferences.getBoolean("night_mode", true)

        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun goToLightSensor(view: View) {
        val intent = Intent(this, lightsensor::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun goToSpeechText(view: View) {
        val intent = Intent(this, speechtotext::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun onExit(view: View) {
        this.finishAffinity()
    }

    fun goToVibrate(view: View) {
        val intent = Intent(this, vibration::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun goToPictureVoice(view: View) {
        val intent = Intent(this, picturevoice::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun goToImageRecognition(view: View) {
        val intent = Intent(this, imagerecognition::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun chooseTheme(view: View){
        val intent = Intent(this, lightdark::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun aboutDialog(view: View) {
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
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.confirm_password)

        val confirmPassword = EditText(this)

        confirmPassword.setHint("Enter Text")
        confirmPassword.inputType = InputType.TYPE_CLASS_NUMBER
        builder.setView(confirmPassword)

        builder.setPositiveButton("OK") { dialog, which ->
            var passWord = confirmPassword.text.toString()
            if (passWord == dbHelper.getPasscode().last().passcode) {
                dbHelper.deleteAll()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Incorrect Password Entered", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        confirmPassword.gravity = Gravity.CENTER

        builder.show()
    }

    fun showLanguageDialog(view: View) {
        val listItems = arrayOf("French", "German", "Italian", "English")
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(R.string.choose_language)
        builder.setSingleChoiceItems(listItems, -1) { dialog, i ->
            if (i == 0) {
                //French
                setLocale("fr")
                recreate()
            } else if (i == 1) {
                //German
                setLocale("de")
                recreate()
            } else if (i == 2) {
                //Italian
                setLocale("it")
                recreate()
            } else if (i == 3) {
                //English
                setLocale("en")
                recreate()
            }
            dialog.dismiss()
        }
        val languageChoose = builder.create()
        languageChoose.show()
    }

    @Suppress("DEPRECATION")
    private fun setLocale(lang: String?) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        val editor = getSharedPreferences("Settings", MODE_PRIVATE).edit()
        editor.putString("My_Lang", lang)
        editor.apply()
    }

    fun loadLocale() {
        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val language = prefs.getString("My_Lang", "")
        setLocale(language)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }


}