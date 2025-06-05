package com.example.edutasker.repo.professorDatabase

import com.example.edutasker.db.entities.ProfessorEntity
import com.example.edutasker.model.StudentPreviewAsListModel

interface IProfessorDatabaseRepository {
    suspend fun loginProfessor(email: String, password: String): ProfessorEntity?
    suspend fun insertProfessor(professor: ProfessorEntity)
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?
    suspend fun getAllStudentsOfProfessorSubjects(
        idProfessor: String,
        specificSubject: String? = null,
    ): List<StudentPreviewAsListModel>

    suspend fun getProfessorSubjects(
        idProfessor: String,
        selectedStudent: StudentPreviewAsListModel? = null
    ): List<String>
}