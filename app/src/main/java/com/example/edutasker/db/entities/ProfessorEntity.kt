package com.example.edutasker.db.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["email"], unique = true)])
data class ProfessorEntity(
    @PrimaryKey val profId: String,
    val username: String,
    val name: String,
    val email:String,
    val password: String,
    val image:String,
    val subjects :List<String>
)