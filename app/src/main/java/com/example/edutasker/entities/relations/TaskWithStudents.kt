package com.example.edutasker.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity

data class TaskWithStudents(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "studentId",
        associateBy = Junction(TaskStudentCrossRef::class)
    )
    val students: List<StudentEntity>,
)
