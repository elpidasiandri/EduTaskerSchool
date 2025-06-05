package com.example.edutasker.useCases.professor

import com.example.edutasker.db.entities.ProfessorEntity
import com.example.edutasker.repo.professorDatabase.IProfessorDatabaseRepository

class LoginProfessorUseCase(
    private val repo: IProfessorDatabaseRepository
) {
    suspend operator fun invoke(email: String, password: String): ProfessorEntity? {
        return repo.loginProfessor(email, password)
    }
}
