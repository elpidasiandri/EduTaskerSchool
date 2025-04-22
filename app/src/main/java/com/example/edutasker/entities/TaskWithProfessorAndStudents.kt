package com.example.edutasker.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class TaskWithProfessorAndStudents(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "assignBy",
        entityColumn = "profId"
    ) val professor: ProfessorEntity,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "taskId",
        associateBy = Junction(TaskStudentCrossRef::class)
    ) val students: List<StudentEntity>
)

@Entity(primaryKeys = ["taskId", "studentId"])
data class TaskStudentCrossRef(
    val taskId: String,
    val studentId: String
)
