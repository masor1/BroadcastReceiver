package com.masorone.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "loaded" -> {
                val percent = intent.getIntExtra("percent", 0)
                Toast.makeText(context, "percent: $percent", Toast.LENGTH_SHORT).show()
            }
            ACTION_CLICKED -> {
                val clickedCount = intent.getIntExtra(CLICKED_COUNT, 0)
                Toast.makeText(context, "ACTION_CLICKED: $clickedCount", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(context, "ACTION_BATTERY_LOW", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnedOn = intent.getBooleanExtra("state", false)
                Toast.makeText(
                    context,
                    "ACTION_AIRPLANE_MODE_CHANGED: $turnedOn",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    companion object {

        const val ACTION_CLICKED = "clicked"

        const val CLICKED_COUNT = "clicked_count"
    }
}