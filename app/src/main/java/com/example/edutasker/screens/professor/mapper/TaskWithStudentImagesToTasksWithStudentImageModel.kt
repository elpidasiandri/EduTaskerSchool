package com.example.edutasker.screens.professor.mapper

import com.example.edutasker.entities.relations.TaskWithStudents
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.model.TasksWithStudentImageModel


fun TaskWithStudents.taskDomainToTasksWithStudentImageModel(): TasksWithStudentImageModel {
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
    val studentImage = students.firstOrNull()?.image.orEmpty()
    return TasksWithStudentImageModel(
        task = taskModel,
        studentImage = studentImage
    )
}