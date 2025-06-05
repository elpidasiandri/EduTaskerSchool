package com.example.edutasker.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = StudentEntity::class,
            parentColumns = ["studentId"],
            childColumns = ["assignTo"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class TaskEntity(
    @PrimaryKey val taskId: String,
    val taskTitle: String,
    val subjectName: String,
    val description: String,
    val assignBy: String,
    val deadlineDate: String,
    val creationDate: String,
    val assignTo: String,
    val progress: String
)