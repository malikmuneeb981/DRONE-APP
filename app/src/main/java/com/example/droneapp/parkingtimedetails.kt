package com.example.droneapp

class parkingtimedetails {
    var parkingzone:String?=null
    var parkinghours:String?=null
    var carnum:String?=null
    var parkingpay:String?=null

    constructor(){

    }
    constructor(parkingzone:String,parkinghours:String,carnum:String,parkingpay:String)
    {
        this.parkingzone=parkingzone
        this.parkinghours=parkinghours
        this.carnum=carnum
       this.parkingpay=parkingpay
    }
}