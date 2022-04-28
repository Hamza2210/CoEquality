package com.example.coequality

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout

class mainmenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        //variable created to retrieve the shared preference named "night" to retrieve the current light/dark mode value
        var sharedPreferences = getSharedPreferences("night", 0)

        //boolean value assigned to shared preference to check to see if it is active
        val booleanValue = sharedPreferences.getBoolean("night_mode", true)

        //if the shared preference contains dark mode to be true, the background is dynamically set to dark and mode is
        if (booleanValue) {
            findViewById<ConstraintLayout>(R.id.mainmenu).setBackgroundResource(R.drawable.lightdark_bg)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        //if the shared preference contains dark mode to be `false`, the background is dynamically set to light and mode is
        else{
            findViewById<ConstraintLayout>(R.id.mainmenu).setBackgroundResource(R.drawable.lightbackground)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }

    override fun onBackPressed(){
        this.finishAffinity()
    }

    fun exitApp(view: View){
        this.finishAffinity()
    }

    //Button method used to direct the user to the select screen from the menu
    fun goToToolSelect(view: View){
        val intent = Intent(this, toolselect::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }


}