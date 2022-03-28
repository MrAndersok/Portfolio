package com.example.notecalendar.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo (name = "day")val Day: Int,
    @ColumnInfo (name = "month")val Month: Int,
    @ColumnInfo (name = "note")val Note: String
)
{
    constructor(day: Int, month: Int, note: String) : this(0, day, month, note)
}
