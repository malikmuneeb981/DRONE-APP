package com.example.droneapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_choosingparkinglot.*
import kotlinx.android.synthetic.main.layout_list.view.*

class choosingparkinglot : AppCompatActivity() {
    lateinit var mSearchText : EditText
    lateinit var mRecyclerView : RecyclerView

    lateinit var mDatabase : DatabaseReference

    lateinit var FirebaseRecyclerAdapter : FirebaseRecyclerAdapter<parkingdetails, UsersViewHolder>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title="Pocket Money"
        setContentView(R.layout.activity_choosingparkinglot)
        btnnext.setOnClickListener {
            startActivity(Intent(this,parkingtime::class.java))
        }

        mSearchText =findViewById(R.id.searchText)
        mRecyclerView = findViewById(R.id.list_view)


        mDatabase = FirebaseDatabase.getInstance().getReference("parking areas")


        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))




        mSearchText.addTextChangedListener(object  : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val searchText = mSearchText.getText().toString().trim()

                loadFirebaseData(searchText)
            }
        } )

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.btnsignout->{
                val builder=AlertDialog.Builder(this)
                builder.setTitle("ARE YOU SURE?")
                builder.setMessage("YOU WANT TO SIGNOUT?")
                builder.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> finish()
                    FirebaseAuth.getInstance().signOut()
                    val intent=Intent(this,register::class.java )
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                })
                builder.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
                builder.show()
            }
            R.id.btnsettings->{
                val intent=Intent(this,settings::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadFirebaseData(searchText : String) {

        if(searchText.isEmpty()){

            FirebaseRecyclerAdapter.cleanup()
            mRecyclerView.adapter = FirebaseRecyclerAdapter

        }else {


            val firebaseSearchQuery = mDatabase.orderByChild("parkingcity").startAt(searchText).endAt(searchText + "\uf8ff")

            FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<parkingdetails, UsersViewHolder>(

                parkingdetails::class.java,
                R.layout.layout_list,
                UsersViewHolder::class.java,
                firebaseSearchQuery


            ) {
                override fun populateViewHolder(viewHolder: UsersViewHolder, model: parkingdetails?, position: Int) {


                    viewHolder.mview.tvparkingarea.setText("CITY:"+model?.parkingarea)
                    viewHolder.mview.tvparkingcity.setText("AREA:"+model?.parkingcity)
                    viewHolder.mview.tvparkingplot.setText("PLOT:"+model?.parkingplot)

                    viewHolder.mview.btnuselocationrecycleview.setOnClickListener()
                    {
                        val dialog= AlertDialog.Builder(this@choosingparkinglot).setTitle("ARE YOU SURE!")
                            .setMessage("YOU WANT TO USE THIS LOCATION")
                            .setPositiveButton("CONFIRM",{ dialogInterface: DialogInterface, i: Int -> Toast.makeText(this@choosingparkinglot,"DONE",
                                Toast.LENGTH_SHORT).show()})
                            .setNegativeButton("CANCEL",{ dialogInterface: DialogInterface, i: Int -> finish()})
                        dialog.show()
                    }

                }

            }

            mRecyclerView.adapter = FirebaseRecyclerAdapter

        }
    }


    // // View Holder Class

    class UsersViewHolder(var mview : View) : RecyclerView.ViewHolder(mview) {

    }
    }

