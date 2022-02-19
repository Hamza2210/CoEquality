package com.example.coequality

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.DialogInterface
import android.content.SharedPreferences
import android.app.Activity
import android.content.res.Configuration
import androidx.appcompat.app.AlertDialog
import java.util.*

class test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
    }

    private fun showLangaugeDialog() {
        val listItems = arrayOf("French", "German", "Italian", "English")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Language")
        builder.setSingleChoiceItems(listItems, -1) { dialog, i ->
            if (i == 0) {
                //French
                setLocale("fr-rFR")
                recreate()
            } else if (i == 1) {
                //German
                setLocale("de-rDE")
                recreate()
            } else if (i == 2) {
                //Italian
                setLocale("it-rIT")
                recreate()
            } else if (i == 3) {
                //English
                setLocale("en")
                recreate()
            }
            dialog.dismiss()
        }
        val mDialog = builder.create()
        mDialog.show()
    }

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
}