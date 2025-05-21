package com.example.edutasker.mapper

import com.example.edutasker.entities.relations.TaskWithStudentAndProfessor
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.ProfessorBasicModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus

fun TaskWithStudentAndProfessor.toOpenedTask(): OpenedTaskModel {
    return OpenedTaskModel(
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
            username = "${student.username} ${student.name}",
            image = student.image
        ),
        professorBasic = ProfessorBasicModel(
            id = professor.profId,
            username = "${professor.username} ${professor.name}",
            image = professor.image,
        )
    )
}
