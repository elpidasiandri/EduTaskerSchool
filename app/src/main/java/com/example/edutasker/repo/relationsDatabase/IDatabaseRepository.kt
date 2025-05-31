package com.example.edutasker.repo.relationsDatabase

import com.example.edutasker.entities.relations.TaskWithStudent
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TasksWithStudentImageModel
import kotlinx.coroutines.flow.Flow

interface IDatabaseRepository {
    suspend fun getNameIdAndImageOfStudents(): List<StudentPreviewAsListModel>
    suspend fun searchStudents(keyword: String): List<StudentPreviewAsListModel>
    suspend fun getAllTasksOfAllStudents(): Flow<List<TaskWithStudent>>
    suspend fun getAllTasksOfProfessorStudent(): List<TasksWithStudentImageModel>
    suspend fun getAllTasksBySpecificProfessorOfStudent(studentId: String): Flow<List<TaskWithStudent>>
    suspend fun getAllInfoAboutTaskAndBasicOfStudentAndProfessor(taskId: String): OpenedTaskModel
}