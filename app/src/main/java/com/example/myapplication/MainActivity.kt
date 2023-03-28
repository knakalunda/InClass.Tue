package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView

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

        Thread{
            for(i in 100 downTo 1){
                Log.d("Countdown", i.toString())
                Thread.sleep(1000)

                timerHandler.sendEmptyMessage(i)
            }
        }.start()
    }
}