package com.example.edutasker.useCases.notification

import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class GetAllNotificationsWithDetailsUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(): List<NotificationsDetails> {
        return repo.getNotificationsDetails()
    }
}