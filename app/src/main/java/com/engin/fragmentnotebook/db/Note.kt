package com.engin.fragmentnotebook.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note(
    var noteTitle: String,
    var noteText: String?
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}