package com.example.edutasker.repo.taskDatabase

import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.model.SubjectTaskCount
import com.example.edutasker.model.UpdateTaskByProfessorModel

interface ITaskDatabaseRepository {
    suspend fun insertTask(task: TaskEntity)
    suspend fun getTaskCountByProfessor(profId: String): Int
    suspend fun getTaskCountPerSubject(): List<SubjectTaskCount>
    suspend fun getTasksByProfessor(professorId: String): List<TaskEntity>
    suspend fun getTasksForSubject(subjectName: String): List<TaskEntity>
    suspend fun updateTaskByProfessor(taskInfo: UpdateTaskByProfessorModel)
}