package com.example.edutasker.db.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.edutasker.db.entities.StudentEntity
import com.example.edutasker.db.entities.TaskEntity

data class TaskWithStudent(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "assignTo",
        entityColumn = "studentId"
    )
    val student: StudentEntity,
)
