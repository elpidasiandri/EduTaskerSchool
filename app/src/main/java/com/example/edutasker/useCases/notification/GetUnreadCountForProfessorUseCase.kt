package com.example.edutasker.useCases.notification

import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo

class GetUnreadCountForProfessorUseCase(
    private val repo: INotificationDatabaseRepo
) {
    suspend operator fun invoke(professorId: String): Int {
        return repo.getUnreadCountForProfessor(professorId)
    }
}