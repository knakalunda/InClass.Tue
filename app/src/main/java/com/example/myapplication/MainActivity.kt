package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val timerHandler = Handler(Looper.getMainLooper()){ //lambda has no "return"
        timerTextView.text = it.what.toString()

        true
    }

    val timerTextView : TextView by lazy{
        findViewById(R.id.timerTextView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            CoroutineScope(Job() + Dispatchers.Default).launch {
                repeat(100){
                    (100 - it).toString().run{
                        Log.d("Countdown", this)
                        withContext(Dispatchers.Main){
                            timerTextView.text = this@run
                        }
                    }
                    delay(1000)
                }
            }
        }
    }
}