package com.engin.fragmentnotebook.util

import android.view.View
import com.engin.fragmentnotebook.db.Note

interface RecyclerViewClickInterface {
    fun onItemClick(v: View, note: Note)
    fun onItemLongClick(v: View, note: Note)
}