package com.example.edutasker.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity

data class TaskWithStudentAndProfessor(
    @Embedded val task: TaskEntity,

    @Relation(
        parentColumn = "assignTo",
        entityColumn = "studentId"
    )
    val student: StudentEntity,

    @Relation(
        parentColumn = "assignBy",
        entityColumn = "profId"
    )
    val professor: ProfessorEntity
)
