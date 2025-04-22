package com.example.edutasker.useCases.student

import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.repo.IDatabaseRepository

class GetStudentByEmailUseCase(
    private val databaseRepository: IDatabaseRepository
) {
    suspend operator fun invoke(email: String): StudentEntity? {
        return databaseRepository.getStudentByEmail(email)
    }
}