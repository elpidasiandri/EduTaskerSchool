package com.example.edutasker.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity

data class TaskWithStudent(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "assignTo",
        entityColumn = "studentId"
    )
    val student: StudentEntity,
)
