package com.example.edutasker.useCases.professor

import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.repo.IDatabaseRepository

class LoginProfessorUseCase(
    private val databaseRepository: IDatabaseRepository
) {
    suspend operator fun invoke(email: String, password: String): ProfessorEntity? {
        return databaseRepository.loginProfessor(email, password)
    }
}
