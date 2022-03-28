package com.example.coequality

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

class mainmenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)
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