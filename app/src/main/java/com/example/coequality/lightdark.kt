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


        var imageView = findViewById<ImageView>(R.id.imageView)
        var themeChange = findViewById<SwitchCompat>(R.id.switchCompat)
        var sharedPrefs = getSharedPreferences("night", 0)

        val booleanValue = sharedPrefs.getBoolean("night_mode", true)

        if (booleanValue) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            themeChange.isChecked = true
            imageView.setImageResource(R.drawable.night)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

    }

    fun changeTheme(view: View){

        var imageView = findViewById<ImageView>(R.id.imageView)
        var themeChange = findViewById<SwitchCompat>(R.id.switchCompat)
        var sharedPrefs = getSharedPreferences("night", 0)

        if (themeChange.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            themeChange.setChecked(true)
            imageView.setImageResource(R.drawable.night)
            val editor = sharedPrefs.edit()
            editor.putBoolean("night_mode", true)
            editor.commit()

        } else if (!themeChange.isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            themeChange.setChecked(false)
            imageView.setImageResource(R.drawable.day)
            val editor = sharedPrefs.edit()
            editor.putBoolean("night_mode", false)
            editor.commit()
        }

        //recreate()
    }

    fun returnToMenu(view: View){
        val intent = Intent(this, toolselect::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}