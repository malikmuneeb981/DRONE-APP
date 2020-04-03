package com.example.droneapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_addinglocation.*
import kotlinx.android.synthetic.main.activity_parkingtime.*

class parkingtime : AppCompatActivity() {
    var maxid:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title="Parking Registration"
        setContentView(R.layout.activity_parkingtime)
        sendingbookdetailstodatabase()
        btnnextparkingtime.setOnClickListener {
            val parkingzone=tilparkingzone3.text.toString()
            val parkinghours=tilparkinghours3.text.toString()
            val carnum=tilcarnum3.text.toString()
            val parkingpay=tilparkingpay3.text.toString()
            val dbref = FirebaseDatabase.getInstance().getReference().child("parking time")
                .child((maxid + 1).toString())
            val userprofileinformation = parkingtimedetails(parkingzone,parkinghours,carnum,parkingpay)
            if (parkinghoursvalidation()&&carnumvalidation()&&parkingpayvalidation()) {
                dbref.setValue(userprofileinformation).addOnSuccessListener()
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
    }
    fun sendingbookdetailstodatabase()
    {
        val dbref = FirebaseDatabase.getInstance().getReference().child("parking time")
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
    fun parkinghoursvalidation():Boolean
    {
        val parkinghours:String=tilparkinghours3.text.toString()
        if (TextUtils.isEmpty(parkinghours))
        {
            tilparkinghours2.error="You Cant leave this empty"
            return false
        }
        else
        {
            tilparkinghours2.isErrorEnabled=false
            return true
        }
    }
    fun carnumvalidation():Boolean
    {
        val carnum:String=tilcarnum3.text.toString()
        if (TextUtils.isEmpty(carnum))
        {

            tilcarnum2.error="You Cant leave this empty"
            return false
        }
        else
        {
            tilcarnum2.isErrorEnabled=false
            return true
        }
    }
    fun parkingpayvalidation():Boolean
    {
        val parkingpay:String=tilparkingpay3.text.toString()
        if (TextUtils.isEmpty(parkingpay))
        {
            tilparkingpay2.error="You Cant leave this empty"
            return false
        }
        else
        {
            tilparkingpay2.isErrorEnabled=false
            return true
        }
    }
}
