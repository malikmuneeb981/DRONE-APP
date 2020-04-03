package com.example.droneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_adminlocationaddition.*

class adminlocationaddition : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminlocationaddition)
btnadminaddlocation.setOnClickListener {
    startActivity(Intent(this,addinglocation::class.java))
}
    }
}
