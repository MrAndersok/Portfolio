package com.example.notecalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            //months for listner's
        val January = findViewById<TextView>(R.id.textView2)
        val March = findViewById<TextView>(R.id.textView3)
        val May = findViewById<TextView>(R.id.textView4)
        val July = findViewById<TextView>(R.id.textView5)
        val September = findViewById<TextView>(R.id.textView6)
        val November = findViewById<TextView>(R.id.textView7)
        val February = findViewById<TextView>(R.id.textView8)
        val April = findViewById<TextView>(R.id.textView9)
        val June = findViewById<TextView>(R.id.textView10)
        val August = findViewById<TextView>(R.id.textView11)
        val October = findViewById<TextView>(R.id.textView12)
        val December = findViewById<TextView>(R.id.textView13)

        // listner
        January.setOnClickListener{
            listnerSend(1)
        }
        March.setOnClickListener{
            listnerSend(3)
        }
        May.setOnClickListener{
            listnerSend(5)
        }
        July.setOnClickListener{
            listnerSend(7)
        }
        September.setOnClickListener{
            listnerSend(9)
        }
        November.setOnClickListener{
            listnerSend(11)
        }
        February.setOnClickListener{
            listnerSend(2)
        }
        April.setOnClickListener{
            listnerSend(4)
        }
        June.setOnClickListener{
            listnerSend(6)
        }
        August.setOnClickListener{
            listnerSend(8)
        }
        October.setOnClickListener{
            listnerSend(10)
        }
        December.setOnClickListener{
            listnerSend(12)
        }
    }

    fun listnerSend(month: Int)
    {
        val intent = Intent(this,Open_month::class.java)
        intent.putExtra("month",month)
        startActivity(intent)
    }

}