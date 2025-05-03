package com.example.roomcomplete

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: NoteRepository
    val notes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repo = NoteRepository(dao)
        notes = repo.notes.asLiveData()
    }

    fun addNote(title: String, text: String) = viewModelScope.launch {
        repo.addNote(Note(title = title, text = text))
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repo.deleteNote(note)
    }
}