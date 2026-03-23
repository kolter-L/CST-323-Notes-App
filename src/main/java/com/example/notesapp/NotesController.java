package com.example.notesapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private static final Logger logger = LogManager.getLogger(NotesController.class);

    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * Fetches all notes and displays them on the index page.
     *
     * @param model: the Spring MVC model
     * @return the notes/index view
     */
    @GetMapping
    public String index(Model model) {
        logger.info("Fetching all notes");
        model.addAttribute("notes", noteService.getAllNotes());
        return "notes/index";
    }

    /**
     * Loads the create note form with an empty Note object.
     *
     * @param model
     * @return the notes/create view
     */
    @GetMapping("/create")
    public String createForm(Model model) {
        logger.info("Loading create note form");
        model.addAttribute("newNote", new Note());
        return "notes/create";
    }

    /**
     * Handles create form submission, saves the new note, and redirects to the index.
     *
     * @param note: the note object filled in from the form
     * @return redirect to /notes
     */
    @PostMapping("/create")
    public String create(@ModelAttribute("newNote") Note note) {
        logger.info("Creating note with name: {}", note.getName());
        noteService.createNote(note);
        return "redirect:/notes";
    }

    /**
     * Deletes the note with the given ID and redirects to the index.
     *
     * @param id: the ID of the note to delete
     * @return redirect to /notes
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        logger.info("Deleting note with id: {}", id);
        noteService.deleteNote(id);
        return "redirect:/notes";
    }

    /**
     * Loads the update form pre-populated with the existing data for the given note ID.
     *
     * @param id: the ID of the note to update
     * @param model: the Spring MVC model
     * @return the notes/update view
     */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model) {
        logger.info("Loading update form for note with id: {}", id);
        Note note = noteService.getNoteById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
        model.addAttribute("noteForm", note);
        return "notes/update";
    }

    /**
     * Handles update form submission, updates the note, and redirects to the notes index page.
     *
     * @param id: the ID of the note to update
     * @param note: the Note object populated from the form
     * @return redirect to /notes
     */
    @PostMapping("/update/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute("noteForm") Note note) {
        logger.info("Updating note with id: {}", id);
        noteService.updateNote(id, note);
        return "redirect:/notes";
    }
}
