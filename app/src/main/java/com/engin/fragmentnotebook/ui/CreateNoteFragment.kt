package com.engin.fragmentnotebook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.engin.fragmentnotebook.R
import com.engin.fragmentnotebook.base.BaseFragment
import com.engin.fragmentnotebook.db.Note
import com.engin.fragmentnotebook.db.NoteDatabase
import com.engin.fragmentnotebook.util.toastShort
import kotlinx.coroutines.launch

class CreateNoteFragment : BaseFragment() {
    private lateinit var noteTitle: EditText
    private lateinit var noteText: EditText
    private lateinit var upsertBtn: Button
    private var note: Note? = null

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
        arguments?.let {
            note = CreateNoteFragmentArgs.fromBundle(it).note
            noteTitle.setText(note?.noteTitle)
            noteText.setText(note?.noteText)
            if (note?.noteTitle != null) {
                upsertBtn.text = "Update"
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteTitle = view.findViewById(R.id.editTextNoteTitle)
        noteText = view.findViewById(R.id.editTextNote)
        upsertBtn = view.findViewById(R.id.upsertBtn)
        upsertBtn.setOnClickListener { _view ->
            if (noteTitle.text.isEmpty()) {
                Toast.makeText(requireActivity(), "Title is required.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            launch {
                context?.let {
                    val mNote =
                        Note(noteTitle.text.toString().trim(), noteText.text.toString().trim())
                    if (note == null) {
                        NoteDatabase(it).getNoteDao().addNote(mNote)
                        it.toastShort("Note saved.")
                    } else {
                        mNote.id = note!!.id
                        NoteDatabase(it).getNoteDao().updateNote(mNote)
                        it.toastShort("Note updated.")
                    }
                    val action = CreateNoteFragmentDirections.actionUpsertNote()
                    Navigation.findNavController(_view).navigate(action)
                }
            }
        }
    }


}