package com.example.edutasker.repo

import com.example.edutasker.dao.ProfessorDao
import com.example.edutasker.dao.StudentDao
import com.example.edutasker.dao.TaskDao
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.entities.relations.TaskStudentCrossRef
import com.example.edutasker.entities.relations.TaskWithStudents
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentBasicInfoForPreviewIntoList
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.SubjectTaskCount
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TasksWithStudentImageModel
import com.example.edutasker.screens.professor.mapper.taskDomainToTasksWithStudentImageModel

class DatabaseRepositoryImpl(
    private val professorDao: ProfessorDao,
    private val studentDao: StudentDao,
    private val taskDao: TaskDao,
) : IDatabaseRepository {
    override suspend fun insertProfessor(professor: ProfessorEntity) {
        if (professorDao.isEmailExists(professor.email) == 0) {
            professorDao.insertProfessor(professor)
        }
    }

    override suspend fun loginProfessor(email: String, password: String): ProfessorEntity? {
        return try {
            professorDao.loginProfessor(email, password)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertStudent(student: StudentEntity) {
        if (studentDao.isEmailExists(student.email) == 0) {
            studentDao.insertStudent(student)
        }
    }

    override suspend fun loginStudent(email: String, password: String): StudentEntity? {
        return try {
            studentDao.loginStudent(email, password)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun assignTaskToStudents(taskId: String, studentIds: List<String>) {
        studentIds.forEach { studentId ->
            if (taskDao.isTaskStudentCrossRefExists(taskId, studentId) == 0) {
                val crossRef = TaskStudentCrossRef(taskId = taskId, studentId = studentId)
                taskDao.insertTaskStudentCrossRef(crossRef)
            }
        }
    }

    override suspend fun insertTask(task: TaskEntity) {
        if (taskDao.isTaskIdExists(task.taskId) == 0) {
            if (task.taskId.isEmpty()) {
                task.assignTo.map {
                    taskDao.insertTask(task.copy(taskId = getNewTaskId(getLastTaskId())))
                }
            } else {
                taskDao.insertTask(task.copy(taskId = task.taskId))
            }
        }
    }

    private fun getNewTaskId(oldId: String): String {
        val number = oldId.removePrefix("t").toInt() + 1
        val newId = "t$number"
        return newId
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

    override suspend fun getTasksForStudent(studentId: String): List<TaskWithStudents> {
        return studentDao.getTasksForStudent(studentId)
    }

    override suspend fun getTasksForSubject(subjectName: String): List<TaskEntity> {
        return taskDao.getTasksForSubject(subjectName)
    }

    override suspend fun getProfessorByEmail(email: String): ProfessorEntity? {
        return professorDao.getProfessorByEmail(email)
    }

    override suspend fun getStudentByEmail(email: String): StudentEntity? {
        return studentDao.getStudentByEmail(email)
    }

    override suspend fun getAllStudentsOfProfessorSubjects(
        idProfessor: String,
        specificSubject: String?,
    ): List<StudentPreviewAsListModel> {
        val allStudents = studentDao.getAllStudentsBySubject()
        return getStudentsAfterFilter(allStudents, specificSubject, idProfessor)

    }

    override suspend fun searchAllStudentsOfProfessorSubjects(
        keyword: String,
        idProfessor: String,
        specificSubject: String?,
    ): List<StudentPreviewAsListModel> {
        val allStudents = studentDao.searchStudentsByName(keyword)
        return getStudentsAfterFilter(
            allStudents = allStudents,
            specificSubject = specificSubject,
            idProfessor = idProfessor
        )
    }

    private suspend fun getStudentsAfterFilter(
        allStudents: List<StudentBasicInfoForPreviewIntoList>,
        specificSubject: String?,
        idProfessor: String,
    ): List<StudentPreviewAsListModel> {
        val professorSubjects =
            if (specificSubject.isNullOrBlank()) getProfessorSubjects(idProfessor) else listOf(
                specificSubject
            )
        val filteredStudents = allStudents.filter { student ->
            student.subjects.any { it in professorSubjects }
        }.distinctBy { it.studentId }

        return filteredStudents.map {
            StudentPreviewAsListModel(
                studentId = it.studentId,
                username = it.username,
                image = it.image
            )
        }
    }

    override suspend fun getProfessorSubjects(idProfessor: String): List<String> {
        return professorDao.getProfessorById(idProfessor)?.subjects ?: emptyList()
    }

    override suspend fun getNameIdAndImageOfStudents(): List<StudentPreviewAsListModel> {
        return studentDao.getAllIdsNamesImageOfStudents()
    }

    override suspend fun searchStudents(keyword: String): List<StudentPreviewAsListModel> {
        return studentDao.searchStudents(keyword)
    }

    override suspend fun getAllTasksOfAllStudents(): List<TasksWithStudentImageModel> {
        return taskDao.getAllTasksWithStudentImages().map {
            it.taskDomainToTasksWithStudentImageModel()
        }
    }

    override suspend fun getAllTasksOfProfessorStudent(): List<TasksWithStudentImageModel> {
        return taskDao.getTasksByAssignerWithStudentImages(CurrentUser.userId ?: "")
            .map { it.taskDomainToTasksWithStudentImageModel() }
    }

    private suspend fun getLastTaskId(): String {
        return taskDao.getLastTaskId() ?: ""
    }

}