package com.springboot.task_manager.controllers;


import com.springboot.task_manager.dtos.CreateTaskDTO;
import com.springboot.task_manager.dtos.ErrorResponseDTO;
import com.springboot.task_manager.dtos.UpdateTaskDTO;
import com.springboot.task_manager.entities.TaskEntity;
import com.springboot.task_manager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    //Injecting task service
    private final TaskService taskService;
    //Constructor
    public TaskController(TaskService taskService)
    {
        this.taskService=taskService;
    }
    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTasks()
    {
        var tasks=taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id)
    {
        var task=taskService.getTaskById(id);
        if(task==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }


    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task=taskService.addTask(body.getTitle(),body.getDescription(),body.getDeadline());
        return ResponseEntity.ok(task);

    }


    @PatchMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException {
        var task=taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e)
    {
            if(e instanceof ParseException)
            {
                return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid date format"));
            }
            return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }
}
