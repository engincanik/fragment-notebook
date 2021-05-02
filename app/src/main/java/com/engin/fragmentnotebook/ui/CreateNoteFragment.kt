package com.engin.fragmentnotebook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.engin.fragmentnotebook.R
import com.engin.fragmentnotebook.base.BaseFragment
import com.engin.fragmentnotebook.db.Note

class CreateNoteFragment : BaseFragment() {
    private lateinit var noteTitle: EditText
    private lateinit var noteText: EditText
    private lateinit var upsertBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteTitle = view.findViewById(R.id.editTextNoteTitle)
        noteText = view.findViewById(R.id.editTextNote)
        upsertBtn = view.findViewById(R.id.upsertBtn)
        upsertBtn.setOnClickListener {
            if (noteTitle.text.isEmpty()) {
                Toast.makeText(requireActivity(), "Title is required.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val note = Note(noteTitle.text.toString().trim(), noteText.text.toString().trim())
        }
    }


}