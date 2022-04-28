package com.example.coequality

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coequality.model.DataBaseHelper
import com.example.coequality.model.Passcode
import com.hanks.passcodeview.PasscodeView
import com.hanks.passcodeview.PasscodeView.PasscodeViewListener

class MainActivity : AppCompatActivity() {

    val dbHelper: DataBaseHelper = DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Finds the front-end password text box to use
        val passcodeView = findViewById<PasscodeView>(R.id.passcode_view)

        //checks to see if current passcode table is empty
        if (dbHelper.isEmpty()) {
            passcodeView.setPasscodeLength(4).listener = object : PasscodeViewListener {
                //Displays incorrect password method if password entered is incorrect
                override fun onFail() {
                    Toast.makeText(
                        applicationContext, "Incorrect Password", Toast.LENGTH_SHORT
                    ).show()
                }
                //Starts the next screen activity if the passcode entered is correct
                override fun onSuccess(number: String) {
                    val passcodes = Passcode(0, number)
                    dbHelper.addPasscode(passcodes)
                    val intent = Intent(this@MainActivity, animatestartup::class.java)
                    startActivity(intent)
                }
            }

        } else {
            //if passcode table isnt empty, set the local passcode to the entry saved in the table
            passcodeView.localPasscode = dbHelper.getPasscode().last().passcode
            passcodeView.setPasscodeLength(4).listener = object : PasscodeViewListener {
                //Displays incorrect password method if password entered is incorrect
                override fun onFail() {
                    Toast.makeText(
                        applicationContext, "Incorrect Password", Toast.LENGTH_SHORT
                    ).show()
                }

                //Starts the next screen activity if the passcode entered is correct
                override fun onSuccess(number: String) {
                    val intent = Intent(this@MainActivity, animatestartup::class.java)
                    startActivity(intent)
                }
            }
        }

    }

}