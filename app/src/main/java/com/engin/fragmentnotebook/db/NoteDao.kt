package com.engin.fragmentnotebook.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<Note>
}