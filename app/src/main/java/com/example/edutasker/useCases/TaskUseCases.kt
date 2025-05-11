package com.example.edutasker.useCases

import com.example.edutasker.useCases.task.AssignTaskToStudentsUseCase
import com.example.edutasker.useCases.task.GetTaskCountByProfessorUseCase
import com.example.edutasker.useCases.task.GetTaskCountPerSubjectUseCase
import com.example.edutasker.useCases.task.GetTasksByProfessorUseCase
import com.example.edutasker.useCases.task.GetTasksForStudentUseCase
import com.example.edutasker.useCases.task.GetTasksForSubjectUseCase
import com.example.edutasker.useCases.task.InsertTaskUseCase

data class TaskUseCases(
    val insertTask: InsertTaskUseCase,
    val assignTaskToStudents: AssignTaskToStudentsUseCase,
    val getTasksForStudent: GetTasksForStudentUseCase,
    val getTaskCountByProfessor: GetTaskCountByProfessorUseCase,
    val getTaskCountPerSubject: GetTaskCountPerSubjectUseCase,
    val getTasksByProfessor: GetTasksByProfessorUseCase,
    val getTasksForSubject: GetTasksForSubjectUseCase
)
