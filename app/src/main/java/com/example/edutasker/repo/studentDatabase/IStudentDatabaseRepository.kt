package com.example.edutasker.repo.studentDatabase

import com.example.edutasker.db.entities.StudentEntity

interface IStudentDatabaseRepository {
    suspend fun insertStudent(student: StudentEntity)
    suspend fun loginStudent(email: String, password: String): StudentEntity?
    suspend fun getStudentByEmail(email: String): StudentEntity?

}