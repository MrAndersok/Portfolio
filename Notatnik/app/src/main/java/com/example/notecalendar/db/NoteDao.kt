package com.example.notecalendar.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM note WHERE day == :day AND month == :month")
    fun get_notes(day:Int, month:Int):List<Note>

    @Insert
    fun insertAll( ins: Note)
}