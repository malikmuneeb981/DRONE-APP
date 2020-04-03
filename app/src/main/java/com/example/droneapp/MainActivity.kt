package com.example.droneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val animation= AnimationUtils.loadAnimation(this,R.anim.blink_anim)
        Timer().schedule(3000)
        {
            ivlogo.startAnimation(animation)
            startActivity(Intent(this@MainActivity,login::class.java))
        }
    }
}
