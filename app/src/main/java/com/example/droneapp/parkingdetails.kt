package com.example.droneapp

class parkingdetails {
    var parkingcity:String?=null
    var parkingarea:String?=null
    var parkingplot:String?=null

    constructor(){

    }
    constructor(parkingcity:String,parkingarea:String,parkingplot:String)
    {
        this.parkingcity=parkingcity
        this.parkingarea=parkingarea
        this.parkingplot=parkingplot

    }
}