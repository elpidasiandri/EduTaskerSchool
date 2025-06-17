package com.example.edutasker.repo.taskDatabase

import com.example.edutasker.db.dao.StudentDao
import com.example.edutasker.db.dao.TaskDao
import com.example.edutasker.db.entities.TaskEntity
import com.example.edutasker.model.UpdateTaskByProfessorModel
import kotlinx.coroutines.flow.Flow

class TaskDatabaseRepositoryImpl(
    private val taskDao: TaskDao,
    private val studentDao: StudentDao,
) : ITaskDatabaseRepository {
    override suspend fun insertTask(task: TaskEntity): String {
        val finalTask = if (task.taskId.isEmpty())
            task.copy(taskId = getNewTaskId(getLastTaskId()))
        else
            task
        val studentExists = studentDao.isStudentIdExists(finalTask.assignTo)

        if (studentExists != 0) {
            if (taskDao.isTaskIdExists(finalTask.taskId) == 0) {
                taskDao.insertTask(finalTask)
                return finalTask.taskId
            }
        }
        return ""
    }

    override suspend fun updateTaskByProfessor(taskInfo: UpdateTaskByProfessorModel) {
        taskDao.updateTaskDetails(
            taskId = taskInfo.taskId,
            taskTitle = taskInfo.taskTitle,
            description = taskInfo.taskDescription,
            deadlineDate = taskInfo.taskDeadline,
            progress = taskInfo.progress
        )
    }

    override suspend fun updateTaskByStudent(taskId: String, progress: String) {
        taskDao.updateTaskProgress(
            taskId = taskId,
            progress = progress
        )
    }

    override suspend fun getAllTaskByStudentId(studentId: String): Flow<List<TaskEntity?>?> {
        return taskDao.getTasksByStudent(studentId)
    }

    private fun getNewTaskId(oldId: String): String {
        val number = oldId.removePrefix("t").toInt() + 1
        val newId = "t$number"
        return newId
    }

    private suspend fun getLastTaskId(): String {
        return taskDao.getLastTaskId() ?: ""
    }
}