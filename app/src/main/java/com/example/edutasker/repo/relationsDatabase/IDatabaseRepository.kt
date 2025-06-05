package com.example.edutasker.repo.relationsDatabase

import com.example.edutasker.db.entities.relations.TaskWithStudent
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.StudentPreviewAsListModel
import kotlinx.coroutines.flow.Flow

interface IDatabaseRepository {
    suspend fun getNameIdAndImageOfStudents(): List<StudentPreviewAsListModel>
    suspend fun searchStudents(keyword: String): List<StudentPreviewAsListModel>
    suspend fun getAllTasksOfAllStudents(): Flow<List<TaskWithStudent>>
    suspend fun getAllTasksBySpecificProfessorOfStudent(studentId: String): Flow<List<TaskWithStudent>>
    suspend fun getAllInfoAboutTaskAndBasicOfStudentAndProfessor(taskId: String): OpenedTaskModel
}