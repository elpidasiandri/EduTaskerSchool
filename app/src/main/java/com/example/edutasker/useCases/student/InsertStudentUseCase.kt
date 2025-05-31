package com.example.edutasker.useCases.student

import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.repo.studentDatabase.IStudentDatabaseRepository

class InsertStudentUseCase(
    private val repo: IStudentDatabaseRepository
) {
    suspend operator fun invoke(student: StudentEntity) {
        repo.insertStudent(student)
    }
}
