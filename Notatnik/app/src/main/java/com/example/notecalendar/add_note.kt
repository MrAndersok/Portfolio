package com.example.notecalendar

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.notecalendar.db.AppDatabase
import com.example.notecalendar.db.Note

class add_note : AppCompatActivity() {
    lateinit var addNote : TextView
    lateinit var textNote : EditText
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        val day = intent.getIntExtra("day",0)
        val month = intent.getIntExtra("month",0)
        addNote = findViewById(R.id.textView21)
        addNote.setOnClickListener { addfun(day,month) }
        textNote = findViewById(R.id.editTextTextPersonName)
    }
    fun addfun(day:Int, month:Int){
        if (textNote.text.isEmpty())
        {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
        }
        else
        {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().build()
            val noteDao = db.noteDao()
            noteDao.insertAll(Note(day,month,textNote.text.toString()))
            val intent = Intent(this,Open_day::class.java)
            intent.putExtra("day",day)
            intent.putExtra("month",month)
            startActivity(intent)
            finish()
        }
    }
}