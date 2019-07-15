package com.wynnsolutionsmyanmar.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_passcode.*

class Create_passcode : AppCompatActivity() {

    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_passcode)

        val sharedPreference: SharedPreference = SharedPreference(this)


        btnSave.setOnClickListener {
            val code = pinCode.text.toString()

            val cfm_code = pinCfm.text.toString()
            if (code.equals(cfm_code)) {

                sharedPreference.save("code", code)

                val pc = sharedPreference.getValueString("code")
                Toast.makeText(this, "Passcode Stored ", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))

            } else {
                Toast.makeText(this, "Passcode not match" + code + " not= " + cfm_code, Toast.LENGTH_LONG).show()
            }
        }

    }


}