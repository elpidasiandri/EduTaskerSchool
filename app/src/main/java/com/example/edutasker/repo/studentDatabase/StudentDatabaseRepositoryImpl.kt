package com.example.edutasker.repo.studentDatabase

import com.example.edutasker.db.dao.StudentDao
import com.example.edutasker.db.entities.StudentEntity

class StudentDatabaseRepositoryImpl(
    private val studentDao: StudentDao,
) : IStudentDatabaseRepository {
    override suspend fun insertStudent(student: StudentEntity) {
        if (studentDao.isEmailExists(student.email) == 0) {
            studentDao.insertStudent(student)
        }
    }

    override suspend fun loginStudent(email: String, password: String): StudentEntity? {
        return try {
            studentDao.loginStudent(email, password)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getStudentByEmail(email: String): StudentEntity? {
        return studentDao.getStudentByEmail(email)
    }
}