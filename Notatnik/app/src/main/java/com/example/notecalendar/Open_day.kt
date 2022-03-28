package com.example.notecalendar

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.notecalendar.db.AppDatabase

class Open_day : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_day)
        val openDay = intent.getIntExtra("day",0)
        val openMonth = intent.getIntExtra("month",0)
        val text_day = findViewById<TextView>(R.id.day_text)
        val text_month = findViewById<TextView>(R.id.month_text)
        text_day.setText(openDay.toString())
        when(openMonth)
        {
            1 ->text_month.setText(R.string.January)
            2 ->text_month.setText(R.string.February)
            3 ->text_month.setText(R.string.March)
            4 ->text_month.setText(R.string.April)
            5 ->text_month.setText(R.string.May)
            6 ->text_month.setText(R.string.June)
            7 ->text_month.setText(R.string.July)
            8 ->text_month.setText(R.string.August)
            9 ->text_month.setText(R.string.September)
            10 ->text_month.setText(R.string.October)
            11 ->text_month.setText(R.string.November)
            12 ->text_month.setText(R.string.December)
        }
        val addNote = findViewById<TextView>(R.id.textView23)
        addNote.setOnClickListener { funclick(openDay,openMonth) }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        val noteDao = db.noteDao()
        val list = findViewById<RecyclerView>(R.id.dwadwadwa)
        val lista = noteDao.get_notes(openDay,openMonth)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = AdapterForList(lista)
    }
    fun funclick(day:Int,month:Int)
    {
        val intent = Intent(this,add_note::class.java)
        intent.putExtra("day",day)
        intent.putExtra("month",month)
        startActivity(intent)
    }
}