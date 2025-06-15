package com.example.edutasker.useCases.notification

import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class GetAllNotificationsForProfessorUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(professorId: String): List<NotificationsDetails> {
        return repo.getAllNotificationsForProfessor(professorId)
    }
}