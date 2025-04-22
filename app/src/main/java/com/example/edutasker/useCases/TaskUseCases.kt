package com.example.edutasker.useCases

import com.example.edutasker.useCases.professor.GetProfessorByEmailUseCase
import com.example.edutasker.useCases.professor.InsertProfessorUseCase
import com.example.edutasker.useCases.student.GetStudentByEmailUseCase
import com.example.edutasker.useCases.student.InsertStudentUseCase
import com.example.edutasker.useCases.task.AssignTaskToStudentsUseCase
import com.example.edutasker.useCases.task.GetTaskCountByProfessorUseCase
import com.example.edutasker.useCases.task.GetTaskCountPerSubjectUseCase
import com.example.edutasker.useCases.task.GetTasksByProfessorUseCase
import com.example.edutasker.useCases.task.GetTasksForStudentUseCase
import com.example.edutasker.useCases.task.GetTasksForSubjectUseCase
import com.example.edutasker.useCases.task.InsertTaskUseCase

data class TaskUseCases(
    val insertStudent: InsertStudentUseCase,
    val insertProfessor: InsertProfessorUseCase,
    val insertTask: InsertTaskUseCase,
    val assignTaskToStudents: AssignTaskToStudentsUseCase,
    val getTasksForStudent: GetTasksForStudentUseCase,
    val getTaskCountByProfessor: GetTaskCountByProfessorUseCase,
    val getTaskCountPerSubject: GetTaskCountPerSubjectUseCase,
    val getTasksByProfessor: GetTasksByProfessorUseCase,
    val getTasksForSubject: GetTasksForSubjectUseCase,
    val getStudentByEmail: GetStudentByEmailUseCase,
    val getProfessorByEmail: GetProfessorByEmailUseCase,
)
