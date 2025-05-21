package com.example.edutasker.mapper

import com.example.edutasker.entities.relations.TaskWithStudents
import com.example.edutasker.model.OpenedTask
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus

fun TaskWithStudents.toOpenedTask(): OpenedTask {
    return OpenedTask(
        taskInfo = TaskModel(
            taskId = task.taskId,
            taskTitle = task.taskTitle,
            subjectName = task.subjectName,
            description = task.description,
            assignTo = task.assignTo,
            assignByProfessor = task.assignBy,
            deadlineDate = task.deadlineDate,
            creationDate = task.creationDate,
            progress = TaskStatus.fromKey(task.progress)
        ),
        studentBasic = StudentPreviewAsListModel(
            studentId = student.studentId,
            username = student.username,
            image = student.image
        )
    )
}
