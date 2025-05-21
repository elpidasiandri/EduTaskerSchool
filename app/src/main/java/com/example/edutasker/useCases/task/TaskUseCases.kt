package com.example.edutasker.useCases.task

import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksBySpecificProfessorOfStudentUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksOfAllStudents
import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksOfProfessorStudentUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetTaskCountByProfessorUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetTaskCountPerSubjectUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetTasksByProfessorUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetTasksForSubjectUseCase
import com.example.edutasker.useCases.task.needOnInitialize.InsertMockDataUseCase
import com.example.edutasker.useCases.task.needOnInitialize.InsertTaskUseCase

data class TaskUseCases(
    val insertTask: InsertTaskUseCase,
    val getTaskCountByProfessor: GetTaskCountByProfessorUseCase,
    val getTaskCountPerSubject: GetTaskCountPerSubjectUseCase,
    val getTasksByProfessor: GetTasksByProfessorUseCase,
    val getTasksForSubject: GetTasksForSubjectUseCase,
    val getAllTasksOfAllStudents: GetAllTasksOfAllStudents,
    val getAllTasksOfProfessorStudent: GetAllTasksOfProfessorStudentUseCase,
    val getAllTasksBySpecificProfessorOfStudent: GetAllTasksBySpecificProfessorOfStudentUseCase,
    val insertMockData: InsertMockDataUseCase,
)
