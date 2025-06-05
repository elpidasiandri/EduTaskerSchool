package com.example.edutasker.useCases.professor

import com.example.edutasker.db.entities.ProfessorEntity
import com.example.edutasker.repo.professorDatabase.IProfessorDatabaseRepository

class InsertProfessorUseCase(
    private val repo: IProfessorDatabaseRepository
) {
    suspend operator fun invoke(professor: ProfessorEntity) {
        repo.insertProfessor(professor)
    }
}
