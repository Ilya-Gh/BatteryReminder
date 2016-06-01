package com.j380.alarm.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.j380.alarm.BatteryService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val batteryIntent = Intent(this, BatteryService::class.java)
        startService(batteryIntent)
    }


}
