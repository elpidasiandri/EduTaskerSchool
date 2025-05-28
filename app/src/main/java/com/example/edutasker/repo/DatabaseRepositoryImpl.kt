package com.example.edutasker.repo

import com.example.edutasker.dao.ProfessorDao
import com.example.edutasker.dao.StudentDao
import com.example.edutasker.dao.TaskDao
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.entities.relations.TaskWithStudent
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentBasicInfoForPreviewIntoList
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.SubjectTaskCount
import com.example.edutasker.model.TasksWithStudentImageModel
import com.example.edutasker.mapper.taskDomainToTasksWithStudentImageModel
import com.example.edutasker.mapper.toOpenedTask
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.UpdateTaskByProfessorModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DatabaseRepositoryImpl(
    private val professorDao: ProfessorDao,
    private val studentDao: StudentDao,
    private val taskDao: TaskDao,
    private val dispatcher: CoroutineDispatcher,
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
            student.subjects.any {
                it in professorSubjects
            }
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

    override suspend fun getAllTasksOfAllStudents(): Flow<List<TaskWithStudent>> {
        return taskDao.getAllTasksWithStudentImages()

    }

    override suspend fun getAllTasksOfProfessorStudent(): List<TasksWithStudentImageModel> {
        return taskDao.getTasksByAssignerWithStudentImages(CurrentUser.getCurrentUserId())
            .map { it.taskDomainToTasksWithStudentImageModel() }
    }

    override suspend fun getAllTasksBySpecificProfessorOfStudent(studentId: String): Flow<List<TaskWithStudent>> {
        return taskDao.getTasksByAssignerAndStudent(
            assignerId = CurrentUser.getCurrentUserId(),
            studentId = studentId
        )

    }

    private suspend fun getLastTaskId(): String {
        return taskDao.getLastTaskId() ?: ""
    }

    override suspend fun getAllInfoAboutTaskAndBasicOfStudentAndProfessor(taskId: String): OpenedTaskModel {
        return taskDao.getTaskWithStudentAndProfessor(taskId).toOpenedTask()
    }

    override suspend fun insertMockData(
        professors: List<ProfessorEntity>,
        students: List<StudentEntity>,
        tasks: List<TaskEntity>,
    ) {
        withContext(dispatcher) {
            professors.forEach { insertProfessor(it) }
            students.forEach { insertStudent(it) }
            tasks.forEach { insertTask(it) }
        }
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
}