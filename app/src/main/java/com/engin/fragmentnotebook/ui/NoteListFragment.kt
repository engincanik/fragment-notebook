package com.engin.fragmentnotebook.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.engin.fragmentnotebook.R
import com.engin.fragmentnotebook.adapter.NotesAdapter
import com.engin.fragmentnotebook.base.BaseFragment
import com.engin.fragmentnotebook.db.Note
import com.engin.fragmentnotebook.db.NoteDatabase
import com.engin.fragmentnotebook.util.RecyclerViewClickInterface
import com.engin.fragmentnotebook.util.toastLong
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class NoteListFragment : BaseFragment(), RecyclerViewClickInterface {
    private lateinit var recyclerView: RecyclerView
    private lateinit var notes: MutableList<Note>
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
        val floatingActionButton: FloatingActionButton = view.findViewById(R.id.createNoteFAB)

        floatingActionButton.setOnClickListener {
            val action = NoteListFragmentDirections.actionCreateNote()
            Navigation.findNavController(it).navigate(action)
        }
        recyclerView = view.findViewById(R.id.note_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                notes = NoteDatabase(it).getNoteDao().getAllNotes()
                recyclerView.adapter = NotesAdapter(notes, this@NoteListFragment)
            }
        }
    }

    override fun onItemClick(v: View, note: Note) {
        val action = NoteListFragmentDirections.actionCreateNote(note)
        Navigation.findNavController(v).navigate(action)
    }

    override fun onItemLongClick(v: View, note: Note) {
        val dialog = AlertDialog.Builder(v.context)
        dialog.setTitle("Do you want to delete it?")
        dialog.setPositiveButton("Yes") { _, _ ->
            launch {
                context?.let {
                    NoteDatabase(it).getNoteDao().deleteNote(note)
                    notes.remove(note)
                    recyclerView.adapter!!.notifyDataSetChanged()
                    it.toastLong("Note has been deleted.")
                }
            }
        }.setNegativeButton("No") { _, _ ->
        }
        dialog.show()
    }
}