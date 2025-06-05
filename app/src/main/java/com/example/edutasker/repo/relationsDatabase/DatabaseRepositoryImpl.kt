package com.example.edutasker.repo.relationsDatabase

import com.example.edutasker.db.dao.StudentDao
import com.example.edutasker.db.dao.TaskDao
import com.example.edutasker.db.entities.relations.TaskWithStudent
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.mapper.toOpenedTask
import com.example.edutasker.model.OpenedTaskModel
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(
    private val studentDao: StudentDao,
    private val taskDao: TaskDao,
) : IDatabaseRepository {

    override suspend fun getNameIdAndImageOfStudents(): List<StudentPreviewAsListModel> {
        return studentDao.getAllIdsNamesImageOfStudents()
    }

    override suspend fun searchStudents(keyword: String): List<StudentPreviewAsListModel> {
        return studentDao.searchStudents(keyword)
    }

    override suspend fun getAllTasksOfAllStudents(): Flow<List<TaskWithStudent>> {
        return taskDao.getAllTasksWithStudentImages()

    }

    override suspend fun getAllTasksBySpecificProfessorOfStudent(studentId: String): Flow<List<TaskWithStudent>> {
        return taskDao.getTasksByAssignerAndStudent(
            assignerId = CurrentUser.getCurrentUserId(),
            studentId = studentId
        )
    }

    override suspend fun getAllInfoAboutTaskAndBasicOfStudentAndProfessor(taskId: String): OpenedTaskModel {
        return taskDao.getTaskWithStudentAndProfessor(taskId).toOpenedTask()
    }
}