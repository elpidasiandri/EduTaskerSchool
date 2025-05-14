package com.example.edutasker.useCases.task

data class TaskUseCases(
    val insertTask: InsertTaskUseCase,
    val assignTaskToStudents: AssignTaskToStudentsUseCase,
    val getTasksForStudent: GetTasksForStudentUseCase,
    val getTaskCountByProfessor: GetTaskCountByProfessorUseCase,
    val getTaskCountPerSubject: GetTaskCountPerSubjectUseCase,
    val getTasksByProfessor: GetTasksByProfessorUseCase,
    val getTasksForSubject: GetTasksForSubjectUseCase
)
