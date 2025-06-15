package com.example.edutasker.repo.taskDatabase

import com.example.edutasker.db.entities.TaskEntity
import com.example.edutasker.model.UpdateTaskByProfessorModel
import kotlinx.coroutines.flow.Flow

interface ITaskDatabaseRepository {
    suspend fun insertTask(task: TaskEntity): String
    suspend fun updateTaskByProfessor(taskInfo: UpdateTaskByProfessorModel)
    suspend fun updateTaskByStudent(taskId: String, progress: String)
    suspend fun getAllTaskByStudentId(studentId: String): Flow<List<TaskEntity?>?>
}