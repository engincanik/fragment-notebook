package com.engin.fragmentnotebook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.engin.fragmentnotebook.R
import com.engin.fragmentnotebook.base.BaseFragment
import com.engin.fragmentnotebook.db.NoteDatabase
import kotlinx.coroutines.launch

class NoteListFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch {
            context?.let {
                val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                println(notes)
            }
        }
    }

}