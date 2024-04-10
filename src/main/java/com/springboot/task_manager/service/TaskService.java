package com.springboot.task_manager.service;

import com.springboot.task_manager.entities.TaskEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskService{
    private ArrayList<TaskEntity> tasks=new ArrayList<>();
    private SimpleDateFormat deadLineFormatter=new SimpleDateFormat("yyyy-MM-dd");
    private int taskId=1;
    public TaskEntity addTask(String title, String description, String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(title);
        task.setDescription(description);
        task.setDeadline(deadLineFormatter.parse(deadline));
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

    public TaskEntity updateTask(int id,String description, String deadline, boolean completed) throws ParseException {
        TaskEntity task = getTaskById(id);

        if (task == null)
        {
            return null;
        }
        if(description!=null)
        {
            task.setDescription(description);
        }
        if(deadline!=null)
        {
            task.setDeadline(deadLineFormatter.parse(deadline));
        }
            task.setCompleted(completed);
        return task;

    }
}
