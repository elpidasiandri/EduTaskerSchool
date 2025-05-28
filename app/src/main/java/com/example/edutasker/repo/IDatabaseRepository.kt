package com.example.edutasker.repo

import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.entities.relations.TaskWithStudent
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.SubjectTaskCount
import com.example.edutasker.model.TasksWithStudentImageModel
import com.example.edutasker.model.UpdateTaskByProfessorModel
import kotlinx.coroutines.flow.Flow

interface IDatabaseRepository {
    suspend fun insertProfessor(professor: ProfessorEntity)
    suspend fun loginProfessor(email: String, password: String): ProfessorEntity?
    suspend fun insertStudent(student: StudentEntity)
    suspend fun loginStudent(email: String, password: String): StudentEntity?
    suspend fun insertTask(task: TaskEntity)
    suspend fun getTaskCountByProfessor(profId: String): Int
    suspend fun getTaskCountPerSubject(): List<SubjectTaskCount>
    suspend fun getTasksByProfessor(professorId: String): List<TaskEntity>
    suspend fun getTasksForSubject(subjectName: String): List<TaskEntity>
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?
    suspend fun getStudentByEmail(email: String): StudentEntity?
    suspend fun getAllStudentsOfProfessorSubjects(
        idProfessor: String,
        specificSubject: String? = null,
    ): List<StudentPreviewAsListModel>

    suspend fun searchAllStudentsOfProfessorSubjects(
        keyword: String,
        idProfessor: String,
        specificSubject: String? = null,
    ): List<StudentPreviewAsListModel>

    suspend fun getProfessorSubjects(idProfessor: String): List<String>
    suspend fun getNameIdAndImageOfStudents(): List<StudentPreviewAsListModel>
    suspend fun searchStudents(keyword: String): List<StudentPreviewAsListModel>
    suspend fun getAllTasksOfAllStudents(): Flow<List<TaskWithStudent>>
    suspend fun getAllTasksOfProfessorStudent(): List<TasksWithStudentImageModel>
    suspend fun getAllTasksBySpecificProfessorOfStudent(studentId: String): Flow<List<TaskWithStudent>>
    suspend fun getAllInfoAboutTaskAndBasicOfStudentAndProfessor(taskId: String): OpenedTaskModel
    suspend fun insertMockData(
        professors: List<ProfessorEntity>,
        students: List<StudentEntity>,
        tasks: List<TaskEntity>,
    )

    suspend fun updateTaskByProfessor(taskInfo: UpdateTaskByProfessorModel)
}