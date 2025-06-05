package com.example.edutasker.db.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["email"], unique = true)])
data class StudentEntity(
    @PrimaryKey val studentId: String,
    val username: String,
    val name: String,
    val image: String,
    val email:String,
    val password: String,
    val subjects: List<String>
)