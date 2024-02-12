package com.example.chapter4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var stopWatch: Chronometer
    var running = false
    var offset: Long = 0
    val offsetKey = "offestKey"
    val runningKey = "runningKey"
    val baseKey = "bbaseKey"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null){
            offset = savedInstanceState.getLong(offsetKey)
            running = savedInstanceState.getBoolean(runningKey)
            if(running){
                stopWatch.base = savedInstanceState.getLong(baseKey)
                stopWatch.start()

            }else{
                setBaseTime()
            }
        }

        stopWatch = findViewById(R.id.stopwatch)
        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            if(!running){
                setBaseTime()
                stopWatch.start()
                running = true
            }
        }

        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            if(running){
                saveOffset()
                stopWatch.stop()
                running = false
            }
        }

        val stopButton = findViewById<Button>(R.id.reset_button)
        stopButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }

        //practice greeting name
        val inputName = findViewById<TextView>(R.id.input_name)
        val buttonName = findViewById<Button>(R.id.greeting_button)
        val displayName = findViewById<TextView>(R.id.display_name)

        buttonName.setOnClickListener {
            displayName.text = inputName.text
        }


    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(offsetKey, offset)
        outState.putBoolean(runningKey, running)
        outState.putLong(baseKey, stopWatch.base)
        super.onSaveInstanceState(outState)

    }

    private fun setBaseTime(){
        stopWatch.base = SystemClock.elapsedRealtime()
    }

    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopWatch.base
    }
}