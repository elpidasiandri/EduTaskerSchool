package com.example.edutasker.useCases.student

import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.repo.IDatabaseRepository

class InsertStudentUseCase(
    private val databaseRepository: IDatabaseRepository
) {
    suspend operator fun invoke(student: StudentEntity) {
        databaseRepository.insertStudent(student)
    }
}
