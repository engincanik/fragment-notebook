package com.engin.fragmentnotebook.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.engin.fragmentnotebook.R
import com.engin.fragmentnotebook.db.Note

class NotesAdapter(private var notes: List<Note>) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitleET: EditText = itemView.findViewById(R.id.editTextNoteTitle)
        val noteTextET: EditText = itemView.findViewById(R.id.editTextNote)

        init {
            itemView.setOnClickListener {
                val position: Int = absoluteAdapterPosition
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}