package com.example.droneapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cc.cloudist.acplibrary.ACProgressConstant
import cc.cloudist.acplibrary.ACProgressFlower
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.dialogresetemailadapter.*

class login : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title="Log In"
        setContentView(R.layout.activity_login)
        var mAuth1 = FirebaseAuth.getInstance()
        btnlogin1.setOnClickListener()
        {
            var mAuth1 = FirebaseAuth.getInstance()
            val intent1 = Intent(this, choosingparkinglot::class.java)
            intent1.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            val email1: String = tilemail4.text.toString()
            val password1: String = tilpassword4.text.toString()
            val adminkey: String = tiladminkey4.text.toString()
            if (emailvalidation() && passwordvalidation()&&((TextUtils.isEmpty(adminkey))))
            {
                mAuth1.signInWithEmailAndPassword(email1, password1)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val dialog = ACProgressFlower.Builder(this).direction(
                                ACProgressConstant.DIRECT_CLOCKWISE
                            )
                                .themeColor(Color.WHITE)
                                .text("loging in please wait..")
                                .fadeColor(Color.DKGRAY).build()
                            dialog.show()

                            // Snackbar.make(cl1,"LOGIN SUCCESSFUL", Snackbar.LENGTH_LONG).show()
                            startActivity(intent1)
                        } else {
                            Toast.makeText(
                                this,
                                "incorrect password or email",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
            }
        }
        btnadminlogin.setOnClickListener()
        {
            var Auth=FirebaseAuth.getInstance()
            val intent=Intent(this,adminlocationaddition::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            val adminemail:String = tilemail4.text.toString()
            val adminpassword:String = tilpassword4.text.toString()
            if (emailvalidation()&&passwordvalidation()&&adminkeyvalidation())
            {
                Auth.signInWithEmailAndPassword(adminemail,adminpassword).addOnCompleteListener (this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val admindialog= ACProgressFlower.Builder(this).direction(
                            ACProgressConstant.DIRECT_CLOCKWISE)
                            .themeColor(Color.WHITE)
                            .text("loging in please wait..")
                            .fadeColor(Color.DKGRAY).build()
                        admindialog.show()

                        // Snackbar.make(cl1,"LOGIN SUCCESSFUL", Snackbar.LENGTH_LONG).show()
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "incorrect password or email", Toast.LENGTH_SHORT).show()
                    }
                })
            }

        }
        btnsignup1.setOnClickListener {
            startActivity(Intent(this,register::class.java))
        }
        btnforgotpassword1.setOnClickListener {
            if (emailvalidation()) {
                val email1: String = tilemail4.text.toString()
                mAuth1.sendPasswordResetEmail(email1).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "EMAIL SENT CHECK YOUR EMAIL", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "EMAIL NOT SENT", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }
    fun emailvalidation(): Boolean {
        val email: String = tilemail4.text.toString()
        if (TextUtils.isEmpty(email)) {
            tilemail1.error = "you cant leave this empty"
            return false
        } else {
            return true
        }
    }
    fun passwordvalidation(): Boolean {
        val password: String = tilpassword4.text.toString()
        if (TextUtils.isEmpty(password)) {
            tilemail1.error = "you cant leave this empty"
            return false
        } else {
            return true
        }
    }
    fun adminkeyvalidation(): Boolean {
        val adminkey: String = tiladminkey4.text.toString()
        if (TextUtils.isEmpty(adminkey)) {
            tiladminkey4.error = "you cant leave this empty"
            return false
        } else if(adminkey=="qwerty") {
            btnlogin1.isEnabled=false
            return true
        }
        else
        {
            Toast.makeText(this,"you have entered the admin key wrong",Toast.LENGTH_SHORT).show()
            return false
        }
    }
}
