package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;

/*Annotate the class with @Controller annotation. @Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 * */
@Controller
public class NoteController {

	

	
	/*
	 * From the problem statement, we can understand that the application
	 * requires us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the collection. Each note 
	 *    should contain Note Id, title, content, status and created date.
	 * 2. Add a new note which should contain the note id, title, content and status.
	 * 3. Delete an existing note.
	 * 4. Update an existing note.
	 */
	
	/* 
	 * Get the application context from resources/beans.xml file using ClassPathXmlApplicationContext() class.
	 * Retrieve the Note object from the context.
	 * Retrieve the NoteRepository object from the context.
	 */
	 	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("beans.xml");
	   	Note note=(Note)applicationContext.getBean("note");
		NoteRepository noteRepository=(NoteRepository)applicationContext.getBean("noteRepository");
	  
	
	/*Define a handler method to read the existing notes by calling the getAllNotes() method 
	 * of the NoteRepository class and add it to the ModelMap which is an implementation of Map 
	 * for use when building model data for use with views. it should map to the default URL i.e. "/" */
		@GetMapping("/")
		public String getNotes(ModelMap model) {
			List<Note> list=noteRepository.getAllNotes();
			model.addAttribute("message", list);
			return "index";
		}
	
	/*Define a handler method which will read the Note data from request parameters and
	 * save the note by calling the addNote() method of NoteRepository class. Please note 
	 * that the createdAt field should always be auto populated with system time and should not be accepted 
	 * from the user. Also, after saving the note, it should show the same along with existing 
	 * notes. Hence, reading notes has to be done here again and the retrieved notes object 
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/saveNote". 
	*/
	 @PostMapping("/saveNote")
	  public String saveNote(ModelMap model,@RequestParam int noteId,@RequestParam String noteTitle,@RequestParam String noteContent,@RequestParam String noteStatus) {
		 Note note=(Note)applicationContext.getBean("note");
		 NoteRepository noteRepository=(NoteRepository)applicationContext.getBean("noteRepository");
		  
		List<Note> list=noteRepository.getAllNotes();
     	  model.addAttribute("message",list);
     	  if((Integer)noteId==null) {
     		 model.addAttribute("noteIdEmpty", true);
             model.addAttribute("addNotes", noteRepository.getAllNotes());
             return "index";
     	  }
     	 if(noteTitle.isEmpty()) {
             model.addAttribute("titleEmpty", true);
             model.addAttribute("addNotes", noteRepository.getAllNotes());
             return "index";
         }
         if(noteContent.isEmpty()) {
             model.addAttribute("contentEmpty", true);
             model.addAttribute("addNotes", noteRepository.getAllNotes());
             return "index";
         }
         if(noteTitle.equals("null")) {
             model.addAttribute("titleNull", true);
             model.addAttribute("addNotes", noteRepository.getAllNotes());
             return "index";
         }
         if(noteContent.equals("null")) {
             model.addAttribute("contentNull", true);
             model.addAttribute("addNotes", noteRepository.getAllNotes());
             return "index";
         }
         note.setNoteId(noteId);
		 note.setNoteTitle(noteTitle);
		 note.setNoteContent(noteContent);
		 note.setNoteStatus(noteStatus);
		 note.setCreatedAt(LocalDateTime.now());
         noteRepository.addNote(note);
		return "index";
	 }
	
	/* Define a handler method to delete an existing note by calling the deleteNote() method 
	 * of the NoteRepository class
	 * This handler method should map to the URL "/deleteNote" 
	*/
	  @GetMapping("/deleteNote")
	  public String deleteNote(@RequestParam int noteId){
		  noteRepository.deleteNote(noteId);
		  return "redirect:/";
	  }
}