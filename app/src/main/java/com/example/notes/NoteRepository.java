package com.example.notes;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(final Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        }).start();
    }

    public void update(final Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.update(note);
            }
        }).start();
    }

    public void delete(final Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        }).start();
    }

    public void deleteAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteAll();
            }
        }).start();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
