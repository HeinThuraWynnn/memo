package com.wynnsolutionsmyanmar.memo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_splash__screen.*

class Splash_Screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)
        setSupportActionBar(toolbar)


        val sharedPreference: SharedPreference = SharedPreference(this)
        val pc = sharedPreference.getValueString("code")
        if(pc != null) {
            startActivity(Intent(this, Login::class.java))
        }
        fabLock.setOnClickListener { view ->


            startActivity(Intent(this, Create_passcode::class.java))

        }





    }

}
