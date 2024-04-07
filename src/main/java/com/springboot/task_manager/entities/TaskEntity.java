package com.springboot.task_manager.entities;

import jdk.jfr.DataAmount;
import lombok.Data;

import java.util.Date;
@Data
public class TaskEntity {
    private int id;
    private String title;
    private String description;
    private Date deadline;
    private boolean completed;

}
