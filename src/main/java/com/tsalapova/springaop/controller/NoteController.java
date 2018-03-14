package com.tsalapova.springaop.controller;

import com.tsalapova.springaop.entity.Note;
import com.tsalapova.springaop.service.NoteService;
import com.tsalapova.springaop.util.PageConstant;
import com.tsalapova.springaop.util.RequestConstant;
import com.tsalapova.springaop.validator.ParameterValidator;
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
    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/notes")
    public String findNotes(ModelMap model, @SessionAttribute("id") Long id) {
        List<Note> notes = noteService.findByUserId(id);
        if(notes==null||notes.isEmpty()){
            model.addAttribute(RequestConstant.MESSAGE, RequestConstant.NO_NOTES);
        }else{
            model.addAttribute("notes", notes);
            model.addAttribute(RequestConstant.CONTENT, "notes");
        }
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/take-note")
    public String displayNote(ModelMap model) {
        model.addAttribute(RequestConstant.CONTENT, "take-note");
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
            model.addAttribute(RequestConstant.CONTENT, "take-note");
        }
        noteService.add(note);
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/view-note")
    public String viewNote(ModelMap model, @RequestParam("note-id") Long noteId) {
        Note note = noteService.findById(noteId);
        model.addAttribute("note", note);
        model.addAttribute(RequestConstant.CONTENT, "note");
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/edit-note")
    public String addNote(ModelMap model, @SessionAttribute("id") Long id, @RequestParam("note-id") Long noteId,
                          @RequestParam("title") String title, @RequestParam("content") String content) {
        Note note = new Note();
        note.setUserId(id);
        note.setNoteId(noteId);
        note.setTitle(title);
        note.setContent(content);
        if (!new ParameterValidator().validateNote(note)) {
            model.addAttribute(RequestConstant.WRONG, RequestConstant.WRONG_INFO);
            model.addAttribute(RequestConstant.CONTENT, "note");
        }
        noteService.update(note);
        return PageConstant.MAIN;
    }

    @RequestMapping(value = "/delete-note")
    public String deleteNote(@RequestParam("note-id") Long noteId) {
        noteService.delete(noteId);
        return PageConstant.MAIN;
    }

}
