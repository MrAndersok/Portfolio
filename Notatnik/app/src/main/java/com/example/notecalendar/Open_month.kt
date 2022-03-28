package com.example.notecalendar

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Open_month : AppCompatActivity() {
    val id_day = "textBlock"
    var month = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_month)
        month = intent.getIntExtra("month", 0)
        val days: Int
        val start:Int
        when(month)
        {
          1,3,5,7,8,10,12 -> days = 31
          4,6,9,11 -> days = 30
          2 -> days = 28
          else -> days = 0
        }
        when(month)
        {
            1,10 -> start = 6
            2,3,11 -> start = 2
            4,7 -> start = 5
            5 -> start = 7
            6 -> start = 3
            8 -> start = 1
            9,12 -> start = 4
            else -> start = 0
        }
        var day = 0
        while(day != days)
        {
            var id = id_day + (start + day).toString()
            var nazar = resources.getIdentifier(id,"id",packageName)
            val block = findViewById<TextView>(nazar)
            block.setVisibility(View.VISIBLE)
            block.setText((day+1).toString())
            block.setOnClickListener{
                onClickDay(block.text.toString().toInt())
            }
            day += 1
        }
    }
    fun onClickDay(day:Int)
    {
        val intent = Intent(this,Open_day::class.java)
        intent.putExtra("day",day)
        intent.putExtra("month",month)
        startActivity(intent)
    }
}