package com.example.noteapplication.viewmodel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.noteapplication.model.Note;
import com.example.noteapplication.network.NoteApiService;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

@HiltViewModel
public class NoteViewModel extends ViewModel
{
    private static final String TAG = "NoteViewModel";

    private final NoteApiService noteApiService;
    private final MutableLiveData<List<Note>> notes = new MutableLiveData<>();

    @Inject
    public NoteViewModel(NoteApiService noteApiService) {
        this.noteApiService = noteApiService;
    }

    public MutableLiveData<List<Note>> getNoteList() {
        return notes;
    }

    public void fetchNotes() {
        noteApiService.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> notes.setValue(result),
                        error -> Log.e(TAG, "getNotes: " + error.getMessage(), error));
    }
}
