package com.springboot.task_manager.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

//Data Transfer object
public class CreateTaskDTO {
    String title;
    String description;
    String deadline;
}
