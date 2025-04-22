package com.example.edutasker.repo

import com.example.edutasker.dao.SubjectTaskCount
import com.example.edutasker.dao.TaskWithStudents
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity

interface IDatabaseRepository {
    suspend fun insertProfessor(professor: ProfessorEntity)
    suspend fun loginProfessor(email: String, password: String): ProfessorEntity?
    suspend fun insertStudent(student: StudentEntity)
    suspend fun loginStudent(email: String, password: String): StudentEntity?
    suspend fun assignTaskToStudents(taskId: String, studentIds: List<String>)
    suspend fun insertTask(task: TaskEntity)
    suspend fun getTaskCountByProfessor(profId: String): Int
    suspend fun getTaskCountPerSubject(): List<SubjectTaskCount>
    suspend fun getTasksByProfessor(professorId: String): List<TaskEntity>
    suspend fun getTasksForStudent(studentId: String): List<TaskWithStudents>
    suspend fun getTasksForSubject(subjectName: String): List<TaskEntity>
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?
    suspend fun getStudentByEmail(email: String): StudentEntity?

}