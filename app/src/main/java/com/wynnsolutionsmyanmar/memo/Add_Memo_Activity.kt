package com.wynnsolutionsmyanmar.memo

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add__memo.*
import android.view.View
import android.widget.CompoundButton
import android.widget.Switch
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


class Add_Memo_Activity : AppCompatActivity() {

    val dbTable="Notes"
    var id=0
    val AES = "AES"
    var encTitle: String = ""
    var encDesc: String = ""
    var decTitle: String = ""
    var decDesc: String = ""
    var enc_dec: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__memo)

        try{
            var bundle:Bundle= intent.extras!!
            id=bundle.getInt("ID",0)
            if(id!=0) {
                etTitle.setText(bundle.getString("name") )
                etDes.setText(bundle.getString("des") )

            }
        }catch (ex:Exception){}

//        enc_decToggle.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
//            if (isChecked) {
//                val sharedPreference = SharedPreference(this)
//                val password = sharedPreference.getValueString("code")
//                encTitle = encrypt(etTitle.text.toString(), password.toString())
//                encDesc  = encrypt(etDes.text.toString(),password.toString())
//                etTitle.setText(encTitle)
//                etDes.setText(encDesc)
//                enc_dec = true
//                Toast.makeText(this,"Encrypted",Toast.LENGTH_LONG).show()
//            } else {
//                val sharedPreference = SharedPreference(this)
//                val password = sharedPreference.getValueString("code")
//                decTitle = decrypt(etTitle.text.toString(), password.toString())
//                decDesc  = decrypt(etDes.text.toString(),password.toString())
//                etTitle.setText(decTitle)
//                etDes.setText(decDesc)
//                enc_dec = false
//                Toast.makeText(this,"Decrypted",Toast.LENGTH_LONG).show()
//
//            }
//        })


    }

    fun btnAdd(view:View){
        var dbManager= DbManager(this)
        var values= ContentValues()
        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())
        if(id==0) {
            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(this, " note is added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, " cannot add note ", Toast.LENGTH_LONG).show()
            }
        }else{
            var selectionArs= arrayOf(id.toString())
            val ID = dbManager.Update(values,"ID=?",selectionArs)
            if (ID > 0) {
                Toast.makeText(this, " note is added", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, " cannot add note ", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun btnEnc(view: View){
        val sharedPreference = SharedPreference(this)
        val password = sharedPreference.getValueString("code")
        encTitle = encrypt(etTitle.text.toString(), password.toString())
        encDesc  = encrypt(etDes.text.toString(),password.toString())
        etTitle.setText(encTitle)
        etDes.setText(encDesc)
        enc_dec = true
        Toast.makeText(this,"Encrypted",Toast.LENGTH_LONG).show()
    }
    fun btnDec(view: View){

        val sharedPreference = SharedPreference(this)
        val password = sharedPreference.getValueString("code")
        decTitle = decrypt(etTitle.text.toString(), password.toString())
        decDesc  = decrypt(etDes.text.toString(),password.toString())
        etTitle.setText(decTitle)
        etDes.setText(decDesc)
        enc_dec = false
        Toast.makeText(this,"Decrypted",Toast.LENGTH_LONG).show()

    }

    @Throws(Exception::class)
    private fun decrypt(outputString: String, password: String): String {
        val key = generateKey(password)
        val c = Cipher.getInstance(AES)
        c.init(Cipher.DECRYPT_MODE, key)
        val decodedValue = Base64.decode(outputString, Base64.DEFAULT)
        val decValue = c.doFinal(decodedValue)
        return String(decValue)
    }

    @Throws(Exception::class)
    private fun encrypt(Data: String, password: String): String {
        val key = generateKey(password)
        val c = Cipher.getInstance(AES)
        c.init(Cipher.ENCRYPT_MODE, key)
        val encVal = c.doFinal(Data.toByteArray())
        return Base64.encodeToString(encVal, Base64.DEFAULT)
    }

    @Throws(Exception::class)
    private fun generateKey(password: String): SecretKeySpec {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = password.toByteArray(charset("UTF-8"))
        digest.update(bytes, 0, bytes.size)
        val key = digest.digest()
        return SecretKeySpec(key, "AES")
    }
}
