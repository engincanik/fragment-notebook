package com.engin.fragmentnotebook.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    var noteTitle: String,
    var noteText: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}