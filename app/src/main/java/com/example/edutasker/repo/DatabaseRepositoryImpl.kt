package com.example.edutasker.repo

import com.example.edutasker.dao.ProfessorDao
import com.example.edutasker.dao.StudentDao
import com.example.edutasker.dao.SubjectTaskCount
import com.example.edutasker.dao.TaskDao
import com.example.edutasker.dao.TaskWithStudents
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.entities.TaskStudentCrossRef

class DatabaseRepositoryImpl(
    private val professorDao: ProfessorDao,
    private val studentDao: StudentDao,
    private val taskDao: TaskDao
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
            taskDao.insertTask(task)
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

}