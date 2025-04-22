package com.example.edutasker.useCases.student

import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.repo.IDatabaseRepository

class LoginStudentUseCase(
    private val databaseRepository: IDatabaseRepository
) {
    suspend operator fun invoke(email: String, password: String): StudentEntity? {
        return databaseRepository.loginStudent(email, password)
    }
}
