package com.example.edutasker.mapper

import com.example.edutasker.db.entities.relations.TaskWithStudentAndProfessor
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.ProfessorBasicModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus

fun TaskWithStudentAndProfessor.toOpenedTask(): OpenedTaskModel {
    return OpenedTaskModel(
        taskInfo = TaskModel(
            taskId = this.task.taskId,
            taskTitle = this.task.taskTitle,
            subjectName = this.task.subjectName,
            description = this.task.description,
            assignTo = this.task.assignTo,
            assignByProfessor = this.task.assignBy,
            deadlineDate = this.task.deadlineDate,
            creationDate = this.task.creationDate,
            progress = TaskStatus.fromKey(this.task.progress)
        ),
        studentBasic = StudentPreviewAsListModel(
            studentId = this.student.studentId,
            username = "${this.student.username} ${this.student.name}",
            image = this.student.image
        ),
        professorBasic = ProfessorBasicModel(
            id = this.professor.profId,
            username = "${this.professor.username} ${this.professor.name}",
            image = this.professor.image,
        )
    )
}
