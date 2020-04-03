package com.example.droneapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_addinglocation.*
import kotlinx.android.synthetic.main.activity_choosingparkinglot.*

class addinglocation : AppCompatActivity() {
    var maxid:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title="Add Location"
        setContentView(R.layout.activity_addinglocation)
        sendingbookdetailstodatabase()
        btnparkingdata.setOnClickListener {
            val cityname: String = tilchoosecity3.text.toString()
            val parkingara: String = tilchooseparkingarea3.text.toString()
            val plotnum: String = tilplotnumber3.text.toString()
            val dbref = FirebaseDatabase.getInstance().getReference().child("parking areas")
                .child((maxid + 1).toString())
            val parkingdetails1 = parkingdetails(cityname, parkingara, plotnum)
            if (citynamevalidation() &&parkingaravalidation()&&plotnumvalidation()) {
                dbref.setValue(parkingdetails1).addOnSuccessListener()
                {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("SUCCESS")
                    builder.setMessage("LOCATION HAS BEEN ADDED TO OUR DATABASE")
                    builder.setPositiveButton("OK",
                        { dialogInterface: DialogInterface, i: Int ->

                        })
                    builder.show()
                }
            }
        }
        fun sendingbookdetailstodatabase() {
            val dbref = FirebaseDatabase.getInstance().getReference().child("books")
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        maxid = dataSnapshot.childrenCount
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            }
            dbref.addValueEventListener(postListener)
        }
    }
    fun sendingbookdetailstodatabase()
    {
        val dbref = FirebaseDatabase.getInstance().getReference().child("books")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = dataSnapshot.childrenCount
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        dbref.addValueEventListener(postListener)
    }
    fun citynamevalidation():Boolean
    {
        val cityname:String=tilchoosecity3.text.toString()
        if (TextUtils.isEmpty(cityname))
        {
            tilchoosecity2.error="You Cant leave this empty"
            return false
        }
        else
        {
            tilchoosecity2.isErrorEnabled=false
            return true
        }
    }
    fun parkingaravalidation():Boolean
    {
        val parkingara:String=tilchooseparkingarea3.text.toString()
        if (TextUtils.isEmpty(parkingara))
        {

            tilchooseparkingarea2.error="You Cant leave this empty"
            return false
        }
        else
        {
            tilchooseparkingarea2.isErrorEnabled=false
            return true
        }
    }
    fun plotnumvalidation():Boolean
    {
        val plotnum:String=tilplotnumber3.text.toString()
        if (TextUtils.isEmpty(plotnum))
        {
            tilplotnumber2.error="You Cant leave this empty"
            return false
        }
        else
        {
            tilplotnumber2.isErrorEnabled=false
            return true
        }
    }

    }

