package com.j380.alarm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val batteryIntent = Intent(this, BatteryService::class.java)
        startService(batteryIntent)
    }


}
