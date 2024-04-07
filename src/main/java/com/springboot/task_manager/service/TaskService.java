package com.springboot.task_manager.service;

import com.springboot.task_manager.entities.TaskEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService{
    private ArrayList<TaskEntity> tasks=new ArrayList<>();
    private int taskId=1;
    public TaskEntity addTask(String title, String description, String deadline)
    {
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        //task.setDeadline(new Date(deadline));
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }


    public ArrayList<TaskEntity> getTasks()
    {
        return tasks;
    }

    public TaskEntity getTaskById(int id)
    {
        //can write this also instead of this old version of loop (stream API)
        //tasks.stream().findAny().filter(task -> task.getId()==id).orElse(null);
        for(TaskEntity task:tasks)
        {
            if(task.getId()==id)
            {
                return task;
            }
        }
        return null;
    }
}
