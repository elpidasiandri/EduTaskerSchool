package com.example.edutasker.repo.taskDatabase

import com.example.edutasker.dao.ProfessorDao
import com.example.edutasker.dao.StudentDao
import com.example.edutasker.dao.TaskDao
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.model.SubjectTaskCount
import com.example.edutasker.model.UpdateTaskByProfessorModel

class TaskDatabaseRepositoryImpl(
    private val taskDao: TaskDao,
    private val studentDao: StudentDao,
    private val professorDao: ProfessorDao,

    ) : ITaskDatabaseRepository {
    override suspend fun insertTask(task: TaskEntity) {
        val finalTask = if (task.taskId.isEmpty())
            task.copy(taskId = getNewTaskId(getLastTaskId()))
        else
            task
        val studentExists = studentDao.isStudentIdExists(finalTask.assignTo)

        if (studentExists != 0) {
            if (taskDao.isTaskIdExists(finalTask.taskId) == 0) {
                taskDao.insertTask(finalTask)
            }
        }
    }

    override suspend fun getTaskCountByProfessor(profId: String): Int {
        return taskDao.getTaskCountForProfessor(profId)
    }

    override suspend fun getTaskCountPerSubject(): List<SubjectTaskCount> {
        return taskDao.getTaskCountPerSubject()
    }

    override suspend fun getTasksByProfessor(professorId: String): List<TaskEntity> {
        return professorDao.getTasksByProfessor(professorId)
    }

    override suspend fun getTasksForSubject(subjectName: String): List<TaskEntity> {
        return taskDao.getTasksForSubject(subjectName)
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

    private fun getNewTaskId(oldId: String): String {
        val number = oldId.removePrefix("t").toInt() + 1
        val newId = "t$number"
        return newId
    }

    private suspend fun getLastTaskId(): String {
        return taskDao.getLastTaskId() ?: ""
    }
}