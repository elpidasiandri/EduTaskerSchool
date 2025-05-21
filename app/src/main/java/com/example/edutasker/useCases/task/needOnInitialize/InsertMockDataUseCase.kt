package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.repo.IDatabaseRepository

class InsertMockDataUseCase(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(
        professors: List<ProfessorEntity>,
        students: List<StudentEntity>,
        tasks: List<TaskEntity>,
    ) {
        return repo.insertMockData(
            professors = professors,
            students = students,
            tasks = tasks
        )
    }
}

