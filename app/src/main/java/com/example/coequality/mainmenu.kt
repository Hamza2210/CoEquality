package com.example.coequality

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class mainmenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        var sharedPreferences = getSharedPreferences("night", 0)

        val booleanValue = sharedPreferences.getBoolean("night_mode", true)

        if (booleanValue) {
            findViewById<ConstraintLayout>(R.id.mainmenu).setBackgroundResource(R.drawable.lightdark_bg)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

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

    fun goToToolSelect(view: View){
        val intent = Intent(this, toolselect::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }


}