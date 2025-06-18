package com.example.edutasker.useCases.notification.common

import com.example.edutasker.repo.notificationDatabase.common.INotificationCommonDatabaseRepo
import kotlinx.coroutines.flow.Flow

class GetUnreadCountForProfessorUseCase(
    private val repo: INotificationCommonDatabaseRepo
) {
    suspend operator fun invoke(professorId: String): Flow<Int> {
        return repo.getUnreadCountForProfessor(professorId)
    }
}