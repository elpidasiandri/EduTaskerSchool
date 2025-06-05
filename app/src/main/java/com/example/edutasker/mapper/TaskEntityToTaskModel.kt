package com.example.edutasker.mapper

import com.example.edutasker.db.entities.TaskEntity
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus

fun TaskEntity.toTaskModel(): TaskModel {
    return TaskModel(
        taskId = this.taskId,
        taskTitle = this.taskTitle,
        subjectName = this.subjectName,
        description = this.description,
        assignTo = this.assignTo,
        assignByProfessor = this.assignBy,
        deadlineDate = this.deadlineDate,
        creationDate = this.creationDate,
        progress = TaskStatus.fromKey(this.progress)
    )
}