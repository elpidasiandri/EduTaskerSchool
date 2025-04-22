package com.example.edutasker.useCases.task

import com.example.edutasker.dao.StudentDao
import com.example.edutasker.dao.TaskWithStudents
import com.example.edutasker.repo.IDatabaseRepository

class GetTasksForStudentUseCase(
    private val repo: IDatabaseRepository
) {
    suspend operator fun invoke(studentId: String): List<TaskWithStudents> {
        return repo.getTasksForStudent(studentId)
    }
}
