package com.example.edutasker.di

import com.example.edutasker.repo.notificationDatabase.common.INotificationCommonDatabaseRepo
import com.example.edutasker.repo.notificationDatabase.common.NotificationCommonDatabaseRepoImpl
import com.example.edutasker.useCases.notification.GetUnreadCountForStudentUseCase
import com.example.edutasker.useCases.notification.InsertNotificationUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByProfessorForTaskUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByStudentForTaskUseCase
import org.koin.dsl.module

val notificationCommonModule = module {

    single<INotificationCommonDatabaseRepo> {
        NotificationCommonDatabaseRepoImpl(get())
    }
    single {
        InsertNotificationUseCase(get())
    }
    single {
        UpdateNotificationReadableByProfessorForTaskUseCase(get())
    }
    single {
        UpdateNotificationReadableByStudentForTaskUseCase(get())
    }

    single {
        GetUnreadCountForStudentUseCase(get())
    }
}