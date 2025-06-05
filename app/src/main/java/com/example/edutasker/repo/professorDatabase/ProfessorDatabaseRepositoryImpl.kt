package com.example.edutasker.repo.professorDatabase

import com.example.edutasker.db.dao.ProfessorDao
import com.example.edutasker.db.dao.StudentDao
import com.example.edutasker.db.entities.ProfessorEntity
import com.example.edutasker.model.StudentBasicInfoForPreviewIntoList
import com.example.edutasker.model.StudentPreviewAsListModel

class ProfessorDatabaseRepositoryImpl(
    private val professorDao: ProfessorDao,
    private val studentDao: StudentDao,
) : IProfessorDatabaseRepository {
    override suspend fun loginProfessor(email: String, password: String): ProfessorEntity? {
        return try {
            professorDao.loginProfessor(email, password)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertProfessor(professor: ProfessorEntity) {
        if (professorDao.isEmailExists(professor.email) == 0) {
            professorDao.insertProfessor(professor)
        }
    }

    override suspend fun getProfessorByEmail(email: String): ProfessorEntity? {
        return professorDao.getProfessorByEmail(email)
    }

    override suspend fun getProfessorSubjects(
        idProfessor: String,
        selectedStudent: StudentPreviewAsListModel?,
    ): List<String> {
        val professorSubjects = professorDao.getProfessorById(idProfessor)?.subjects ?: emptyList()
        return if (selectedStudent == null) {
            professorSubjects

        } else {
            val studentSubjects =
                studentDao.getStudentById(selectedStudent.studentId)?.subjects ?: emptyList()
            val commonSubjects =
                professorSubjects.intersect(studentSubjects.toSet()).toList().sorted()
            commonSubjects
        }
    }

    override suspend fun getAllStudentsOfProfessorSubjects(
        idProfessor: String,
        specificSubject: String?,
    ): List<StudentPreviewAsListModel> {
        val allStudents = studentDao.getAllStudentsBySubject()
        return getStudentsAfterFilter(allStudents, specificSubject, idProfessor)

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
}