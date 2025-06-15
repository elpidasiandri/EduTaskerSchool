package com.example.edutasker.useCases.notification

import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class GetUnreadCountForStudentUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(studentId: String): Int {
        return repo.getUnreadCountForStudent(studentId)
    }
}