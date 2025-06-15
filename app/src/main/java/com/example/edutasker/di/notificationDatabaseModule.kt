package com.example.edutasker.di

import com.example.edutasker.useCases.notification.InsertNotificationUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByProfessorForTaskUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByStudentForTaskUseCase
import org.koin.dsl.module

val notificationDatabaseModule = module {
    single {
        InsertNotificationUseCase(get())
    }
    single {
        UpdateNotificationReadableByProfessorForTaskUseCase(get())
    }
    single {
        UpdateNotificationReadableByStudentForTaskUseCase(get())
    }
}