package com.example.edutasker.useCases.student

import com.example.edutasker.db.entities.StudentEntity
import com.example.edutasker.repo.studentDatabase.IStudentDatabaseRepository

class LoginStudentUseCase(
    private val repo: IStudentDatabaseRepository
) {
    suspend operator fun invoke(email: String, password: String): StudentEntity? {
        return repo.loginStudent(email, password)
    }
}
