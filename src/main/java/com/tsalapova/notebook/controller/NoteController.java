package com.tsalapova.notebook.controller;

import com.tsalapova.notebook.entity.Note;
import com.tsalapova.notebook.service.NoteService;
import com.tsalapova.notebook.util.PageConstant;
import com.tsalapova.notebook.util.RequestConstant;
import com.tsalapova.notebook.util.SessionConstant;
import com.tsalapova.notebook.validator.ParameterValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Controller
public class NoteController {
    private static Log log = LogFactory.getLog(NoteController.class);

    private NoteService noteService;

    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping(value = "/notes")
    public String findNotes(ModelMap model, @SessionAttribute(SessionConstant.ID) Long id) {
        List<Note> notes = noteService.findByUserId(id);
        if (notes == null || notes.isEmpty()) {
            model.addAttribute(RequestConstant.MESSAGE, RequestConstant.NO_NOTES);
        } else {
            model.addAttribute("notes", notes);
            model.addAttribute(RequestConstant.CONTENT, RequestConstant.NOTES);
        }
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/take-note")
    public String displayNote(ModelMap model) {
        model.addAttribute(RequestConstant.CONTENT, RequestConstant.TAKE_NOTE);
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/add-note")
    public String addNote(ModelMap model, @SessionAttribute("id") Long id,
                          @RequestParam("title") String title, @RequestParam("content") String content) {
        Note note = new Note();
        note.setUserId(id);
        note.setTitle(title);
        note.setContent(content);
        if (!new ParameterValidator().validateNote(note)) {
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG_INFO);
            model.addAttribute(RequestConstant.CONTENT, RequestConstant.TAKE_NOTE);
        }
        noteService.add(note);
        log.info("Note added");
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/view-note")
    public String viewNote(ModelMap model, @RequestParam("note-id") Long noteId) {
        Note note = noteService.findById(noteId);
        model.addAttribute("note", note);
        model.addAttribute(RequestConstant.CONTENT, RequestConstant.NOTE);
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/edit-note")
    public String editNote(ModelMap model, @SessionAttribute("id") Long id, @RequestParam("note-id") Long noteId,
                          @RequestParam("title") String title, @RequestParam("content") String content) {
        Note note = new Note();
        note.setUserId(id);
        note.setNoteId(noteId);
        note.setTitle(title);
        note.setContent(content);
        if (!new ParameterValidator().validateNote(note)) {
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG_INFO);
            model.addAttribute(RequestConstant.CONTENT, RequestConstant.NOTE);
        }
        noteService.update(note);
        log.info("Note edited");
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/delete-note")
    public String deleteNote(@RequestParam("note-id") Long noteId) {
        noteService.delete(noteId);
        log.info("Deleted note");
        return PageConstant.MAIN;
    }

}
