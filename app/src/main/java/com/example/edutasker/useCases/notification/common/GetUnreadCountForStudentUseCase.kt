package com.example.edutasker.useCases.notification.common

import com.example.edutasker.repo.notificationDatabase.common.INotificationCommonDatabaseRepo
import kotlinx.coroutines.flow.Flow

class GetUnreadCountForStudentUseCase(
    private val repo: INotificationCommonDatabaseRepo,
) {
    suspend operator fun invoke(studentId: String): Flow<Int> {
        return repo.getUnreadCountForStudent(studentId)
    }
}