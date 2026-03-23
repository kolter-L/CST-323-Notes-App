package com.example.notesapp;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * Retrieves all notes from the database.
     *
     * @return a list of all notes
     */
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Retrieves a single note by its ID.
     *
     * @param id: the ID of the note to retrieve
     * @return an Optional containing the note if found, or empty if not
     */
    public Optional<Note> getNoteById(Integer id) {
        return noteRepository.findById(id);
    }

    /**
     * Saves a new note to the database.
     *
     * @param note: the note to create
     * @return the saved note
     */
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Updates an existing note by ID with new field values and sets the date to the current datetime.
     *
     * @param id: the ID of the note to update
     * @param updated: the Note object containing the new field values
     * @return the updated and saved note
     */
    public Note updateNote(Integer id, Note updated) {
        return noteRepository.findById(id).map(note -> {
            note.setName(updated.getName());
            note.setDate(LocalDateTime.now());
            note.setAuthor(updated.getAuthor());
            note.setNote(updated.getNote());
            return noteRepository.save(note);
        }).orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
    }

    /**
     * Deletes a note from the database by its ID.
     *
     * @param id: the ID of the note to delete
     */
    public void deleteNote(Integer id) {
        noteRepository.deleteById(id);
    }
}
