package com.example.coequality

import androidx.appcompat.app.AppCompatActivity
import com.hanks.passcodeview.PasscodeView
import android.os.Bundle
import com.example.coequality.R
import com.hanks.passcodeview.PasscodeView.PasscodeViewListener
import android.widget.Toast
import android.content.Intent
import com.example.coequality.animatestartup
import com.example.coequality.model.DataBaseHelper
import com.example.coequality.model.Passcode

class returnLogin : AppCompatActivity() {

    val dbHelper: DataBaseHelper = DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return_log_in)


        //Finds the front-end password text box to use
        val passcodeView = findViewById<PasscodeView>(R.id.passcode_view)

        //sets length of the passcode to 4 digits
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
                val passcodes = Passcode(0, number)
                val intent = Intent(this@returnLogin, animatestartup::class.java)
                startActivity(intent)


            }
        }
    }

}