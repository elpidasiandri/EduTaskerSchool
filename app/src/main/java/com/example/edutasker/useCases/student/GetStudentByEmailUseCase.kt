package com.example.edutasker.useCases.student

import com.example.edutasker.db.entities.StudentEntity
import com.example.edutasker.repo.studentDatabase.IStudentDatabaseRepository

class GetStudentByEmailUseCase(
    private val repo: IStudentDatabaseRepository
) {
    suspend operator fun invoke(email: String): StudentEntity? {
        return repo.getStudentByEmail(email)
    }
}