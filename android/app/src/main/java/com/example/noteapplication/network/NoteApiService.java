package com.example.noteapplication.network;

import com.example.noteapplication.model.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface NoteApiService
{
    @GET("notes")
    Observable<List<Note>> getNotes();
}
