package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.dao.TaskDao
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.repo.professorDatabase.IProfessorDatabaseRepository
import com.example.edutasker.repo.studentDatabase.IStudentDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class InsertMockDataUseCase(
    private val studentRepo: IStudentDatabaseRepository,
    private val professorRepo: IProfessorDatabaseRepository,
    private val dispatcher: CoroutineDispatcher,
    private val taskDao: TaskDao
) {
    suspend operator fun invoke(
        professors: List<ProfessorEntity>,
        students: List<StudentEntity>,
        tasks: List<TaskEntity>,
    ) {
        withContext(dispatcher) {
            professors.forEach { professor ->
                professorRepo.insertProfessor(professor)
            }
            students.forEach { student ->
                studentRepo.insertStudent(student)
            }
            tasks.forEach { task -> taskDao.insertTask(task) }
        }
    }
}

