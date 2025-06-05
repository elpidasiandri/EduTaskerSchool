package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.db.dao.NotificationDao
import com.example.edutasker.db.dao.TaskDao
import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.db.entities.ProfessorEntity
import com.example.edutasker.db.entities.StudentEntity
import com.example.edutasker.db.entities.TaskEntity
import com.example.edutasker.repo.professorDatabase.IProfessorDatabaseRepository
import com.example.edutasker.repo.studentDatabase.IStudentDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class InsertMockDataUseCase(
    private val studentRepo: IStudentDatabaseRepository,
    private val professorRepo: IProfessorDatabaseRepository,
    private val dispatcher: CoroutineDispatcher,
    private val taskDao: TaskDao,
    private val notificationDao: NotificationDao
) {
    suspend operator fun invoke(
        professors: List<ProfessorEntity>,
        students: List<StudentEntity>,
        tasks: List<TaskEntity>,
        notifications: List<NotificationEntity>,
    ) {
        withContext(dispatcher) {
            professors.forEach { professor ->
                professorRepo.insertProfessor(professor)
            }
            students.forEach { student ->
                studentRepo.insertStudent(student)
            }
            tasks.forEach { task -> taskDao.insertTask(task) }
            notifications.forEach { notification -> notificationDao.insertNotification(notification) }
        }
    }
}

