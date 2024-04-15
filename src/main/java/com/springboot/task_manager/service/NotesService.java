package com.springboot.task_manager.service;

import com.springboot.task_manager.entities.NoteEntity;
import com.springboot.task_manager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class NotesService {

    private TaskService taskService;
    private HashMap<Integer,TaskNotesHolder> taskNoteHolders=new HashMap<>();

    public NotesService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNotesHolder {
        protected int noteId=1;
        protected ArrayList<NoteEntity> notes=new ArrayList<>();
    }

    public List<NoteEntity> getNotesForTask(int taskId)
    {
        TaskEntity task = taskService.getTaskById(taskId);
        if(task==null)
        {
            return null;
        }
        if(taskNoteHolders.get(taskId)==null)
        {
            taskNoteHolders.put(taskId,new TaskNotesHolder());      //putting key-value pair
        }
        return taskNoteHolders.get(taskId).notes;
    }


    public NoteEntity addNoteForTask(int taskId,String title, String body)
    {
        TaskEntity task=taskService.getTaskById(taskId);
        if(task==null)
        {
            return null;
        }
        if(taskNoteHolders.get(taskId)==null)
        {
            taskNoteHolders.put(taskId, new TaskNotesHolder());
        }

        TaskNotesHolder taskNotesHolder=taskNoteHolders.get(taskId);        //get
        NoteEntity note = new NoteEntity();
        note.setId(taskNotesHolder.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNotesHolder.notes.add(note);
        taskNotesHolder.noteId++;
        return note;
    }
}
