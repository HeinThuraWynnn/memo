package com.wynnsolutionsmyanmar.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import android.content.Intent



class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPreference: SharedPreference = SharedPreference(this)

        val pc = sharedPreference.getValueString("code")

        btnlogin.setOnClickListener {
            val code = passcode.text.toString()
            if (pc === null){
                startActivity(Intent(this, Create_passcode::class.java))

            }
            if (code.equals(pc)) {

                Toast.makeText(this, "Hello ", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))

            } else {
                Toast.makeText(this, "Passcode not match " + pc , Toast.LENGTH_LONG).show()
            }
        }

    }

}
