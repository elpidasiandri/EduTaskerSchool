package com.example.edutasker.useCases.professor

import com.example.edutasker.db.entities.ProfessorEntity
import com.example.edutasker.repo.professorDatabase.IProfessorDatabaseRepository

class GetProfessorByEmailUseCase(
    private val repo: IProfessorDatabaseRepository
) {
    suspend operator fun invoke(email: String): ProfessorEntity? {
        return repo.getProfessorByEmail(email)
    }
}