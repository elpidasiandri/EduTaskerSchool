package com.example.edutasker.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.edutasker.entities.NotificationEntity
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity

data class NotificationWithDetails(
    @Embedded val notification: NotificationEntity,

    @Relation(
        parentColumn = "taskId",
        entityColumn = "taskId"
    )
    val task: TaskEntity,

    @Relation(
        parentColumn = "studentId",
        entityColumn = "studentId"
    )
    val student: StudentEntity,

    @Relation(
        parentColumn = "professorId",
        entityColumn = "profId"
    )
    val professor: ProfessorEntity
)
