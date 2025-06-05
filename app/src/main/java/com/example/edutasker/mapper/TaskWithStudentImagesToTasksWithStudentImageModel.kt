package com.example.edutasker.mapper

import com.example.edutasker.db.entities.relations.TaskWithStudent
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.model.TasksWithStudentImageModel

fun TaskWithStudent.taskDomainToTasksWithStudentImageModel(): TasksWithStudentImageModel {
    val taskModel = TaskModel(
        taskId = task.taskId,
        taskTitle = task.taskTitle,
        subjectName = task.subjectName,
        description = task.description,
        assignTo = task.assignTo,
        assignByProfessor = task.assignBy,
        deadlineDate = task.deadlineDate,
        creationDate = task.creationDate,
        progress = TaskStatus.valueOf(task.progress)
    )
    val studentImage = student.image
    return TasksWithStudentImageModel(
        task = taskModel,
        studentImage = studentImage
    )
}