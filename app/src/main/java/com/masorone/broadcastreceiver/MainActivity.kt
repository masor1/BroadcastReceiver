package com.masorone.broadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private val receiver = MyReceiver()
    private var clickedCount = 0

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        button.setOnClickListener {
            clickedCount++
            intent = Intent(MyReceiver.ACTION_CLICKED).apply {
                putExtra(MyReceiver.CLICKED_COUNT, clickedCount)
            }
            sendBroadcast(intent)
        }

        val intentFilter = IntentFilter().apply {
            addAction(MyReceiver.ACTION_CLICKED)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}