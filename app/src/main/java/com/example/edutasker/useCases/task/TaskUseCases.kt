package com.example.edutasker.useCases.task

import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksBySpecificProfessorOfStudentUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksOfAllStudents
import com.example.edutasker.useCases.task.needOnInitialize.InsertMockDataUseCase
import com.example.edutasker.useCases.task.needOnInitialize.InsertTaskUseCase

data class TaskUseCases(
    val insertTask: InsertTaskUseCase,
    val getAllTasksOfAllStudents: GetAllTasksOfAllStudents,
    val getAllTasksBySpecificProfessorOfStudent: GetAllTasksBySpecificProfessorOfStudentUseCase,
    val insertMockData: InsertMockDataUseCase,
)
