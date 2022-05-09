package com.masorone.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    private val receiver = MyReceiver()
    private var clickedCount = 0

    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar


    private val receiver2 = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "loaded") {
                val percent = intent.getIntExtra("percent", 0)
                progressBar.progress = percent
            }
        }
    }

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
        progressBar = findViewById(R.id.progress_bar)

        val intentFilter = IntentFilter().apply {
            addAction("loaded")
            addAction(MyReceiver.ACTION_CLICKED)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(Intent.ACTION_BATTERY_LOW)
        }
        registerReceiver(receiver, intentFilter)
        registerReceiver(receiver2, intentFilter)
        Intent(this, MyService::class.java).apply {
            startService(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}