package com.example.edutasker.mapper

import com.example.edutasker.db.entities.TaskEntity
import com.example.edutasker.model.TaskModel


fun TaskModel.taskDomainToTaskEntity(): TaskEntity {
    return TaskEntity(
        taskId = this.taskId,
        subjectName = this.subjectName,
        description = this.description,
        assignBy = this.assignByProfessor,
        deadlineDate = this.deadlineDate,
        creationDate = this.creationDate,
        assignTo = this.assignTo,
        progress = this.progress.name,
        taskTitle = this.taskTitle
    )
}