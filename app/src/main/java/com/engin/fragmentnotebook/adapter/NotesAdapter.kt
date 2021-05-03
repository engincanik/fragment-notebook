package com.engin.fragmentnotebook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.engin.fragmentnotebook.R
import com.engin.fragmentnotebook.db.Note

class NotesAdapter(private var notes: List<Note>) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitleET: TextView = itemView.findViewById(R.id.noteTitleCardTv)
        val noteTextET: TextView = itemView.findViewById(R.id.noteTextCardTv)

        init {
            itemView.setOnClickListener {
                val position: Int = absoluteAdapterPosition
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.note_card, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitleET.text = notes[position].noteTitle
        holder.noteTextET.text = notes[position].noteText
    }
}