package com.example.droneapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title="Sign Up"

        setContentView(R.layout.activity_register)
        btnlogin2.setOnClickListener {
            startActivity(Intent(this,login::class.java))
        }
        btnsignup2.setOnClickListener {
            val email: String = tilemail3.text.toString()
            val password: String = tilpassword3.text.toString()
            val mAuth=FirebaseAuth.getInstance()
            if (emailvalidation() && passwordvalidation() && namevalidation()) {
                val dialog = ACProgressFlower.Builder(this).direction(
                    ACProgressConstant.DIRECT_CLOCKWISE
                )
                    .themeColor(Color.WHITE)
                    .text("Registering user please wait..")
                    .textSize(15)
                    .isTextExpandWidth(true)
                    .fadeColor(Color.DKGRAY).build()
                dialog.show()
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            sendinginfotodatabase()
                            dialog.dismiss()
                            Snackbar.make(cl, "REGISTRATION SUCCESSFUL", Snackbar.LENGTH_LONG)
                                .show()
                        } else {
                            Snackbar.make(cl, "REGISTRATION FAILED", Snackbar.LENGTH_LONG).show()
                        }
                    })
            }
        }
        }

fun emailvalidation(): Boolean {
    val email: String = tilemail3.text.toString()
    if (TextUtils.isEmpty(email)) {
        tilemail2.error = "you cant leave this empty"
        return false
    } else {
        tilemail2.isErrorEnabled=false
        return true
    }
}
fun namevalidation():Boolean{
    val firstname: String = tilfirstname3.text.toString()
    val lastname: String = tillastname3.text.toString()
    val username:String=tilusername3.text.toString()
    val city: String=tilcity3.text.toString()
    val mobilenumber: String=tilmobilenumber3.text.toString()
    if (TextUtils.isEmpty(firstname)) {
        tilfirstname2.error = "you cant leave this empty"
        return false
    }
    else if(TextUtils.isEmpty(lastname))
    {
        tillastname2.error="you cant leave this empty"
        return false
    }
    else if(TextUtils.isEmpty(username))
    {
        tilusername2.error="you cant leave this empty"
        return false
    }
    else if(TextUtils.isEmpty(city))
    {
        tilcity2.error="you cant leave this empty"
        return false
    }
    else if(TextUtils.isEmpty(mobilenumber))
    {
        tilmobilenumber2.error="you cant leave this empty"
        return false
    }
    else {
        tillastname2.isErrorEnabled=false
        tilfirstname2.isErrorEnabled=false
        tilusername2.isErrorEnabled=false
        tilcity2.isErrorEnabled=false
        tilmobilenumber2.isErrorEnabled=false
        return true
    }
    }
fun passwordvalidation(): Boolean {
    val password: String = tilpassword3.text.toString()
    val confirmpassword=tilconfirmpassword3.text.toString()
    if (TextUtils.isEmpty(password)) {
        tilpassword2.error = "you cant leave this empty"
        return false
    }
    else if (password!=confirmpassword)
    {
        tilconfirmpassword2.error = "Passwords are not same"
        return false
    }
    else {
        tilpassword2.isErrorEnabled=false
        tilconfirmpassword2.isErrorEnabled=false
        return true
    }
}
    fun sendinginfotodatabase() {
        val firstname: String = tilfirstname3.text.toString()
        val lastname: String = tillastname3.text.toString()
        val username:String=tilusername3.text.toString()
        val city: String=tilcity3.text.toString()
        val mobilenumber: String=tilmobilenumber3.text.toString()
        val password: String = tilpassword3.text.toString()
        val email: String = tilemail3.text.toString()
        val databaseref = FirebaseDatabase.getInstance().getReference().child("users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val userprofileinformation = userinfo(city, email, firstname,lastname,mobilenumber,password,username)
        databaseref.setValue(userprofileinformation)
    }
}

