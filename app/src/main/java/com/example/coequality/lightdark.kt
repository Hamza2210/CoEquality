package com.example.coequality

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import android.widget.ImageView

class lightdark : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lightdark)


       //variables used to retrieve and select the UI elements of the page
        val imageView = findViewById<ImageView>(R.id.imageView)
        val themeChange = findViewById<SwitchCompat>(R.id.switchCompat)

        //variable created to retrieve the shared preference named "night" to retrieve the current light/dark mode value
        val sharedPrefs = getSharedPreferences("night", 0)

        //boolean value assigned to shared preference to check to see if it is active
        val booleanValue = sharedPrefs.getBoolean("night_mode", true)

        //if statement used to see if the last shared prefernce value is true, the app theme will be night mode
        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            //switch is checked to show dark mode is enabled
            themeChange.isChecked = true
            //image view set to the moon image to show dark mode is enabled
            imageView.setImageResource(R.drawable.night)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }

    fun changeTheme(view: View){

        val imageView = findViewById<ImageView>(R.id.imageView)
        val themeChange = findViewById<SwitchCompat>(R.id.switchCompat)
        val sharedPrefs = getSharedPreferences("night", 0)

        //if statement declared to see if the switch compat is checked
        if (themeChange.isChecked) {
            //dark mode is set as the theme of the application
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            //switch is set to true
            themeChange.isChecked = true
            //imageview is set to the moon drawable to indicate dark mode
            imageView.setImageResource(R.drawable.night)

            //shared preference editor is declared here and the boolean value of `true` is written to the dark mode shared preference to be called where needed
            val editor = sharedPrefs.edit()
            editor.putBoolean("night_mode", true)
            editor.apply()

        //if statement declared to see if the switch compat is unchecked
        } else if (!themeChange.isChecked) {
            //light mode is set as the theme of the application
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            //switch is set to unchecked `false`
            themeChange.isChecked = false
            //imageview is set to the sun drawable to indicate dark mode
            imageView.setImageResource(R.drawable.day)

            //shared preference editor is declared here and the boolean value of `false` is written to the dark mode shared preference to be called where needed
            val editor = sharedPrefs.edit()
            editor.putBoolean("night_mode", false)
            editor.apply()
        }

    }

    //method used to direct the user back to the select screen on click
    fun returnToMenu(view: View){
        val intent = Intent(this, toolselect::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}