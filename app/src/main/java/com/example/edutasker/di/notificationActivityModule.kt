package com.example.edutasker.di

import com.example.edutasker.useCases.notification.GetAllNotificationsForProfessorUseCase
import com.example.edutasker.useCases.notification.GetAllNotificationsForStudentUseCase
import com.example.edutasker.useCases.notification.GetAllNotificationsWithDetailsUseCase
import com.example.edutasker.useCases.notification.GetUnreadCountForProfessorUseCase
import com.example.edutasker.useCases.notification.GetUnreadCountForStudentUseCase
import com.example.edutasker.useCases.notification.GetUnreadNotificationsForProfessorUseCase
import com.example.edutasker.useCases.notification.GetUnreadNotificationsForStudentUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByProfessorUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByStudentUseCase
import org.koin.dsl.module

val notificationActivityModule = module {
    single {
        GetAllNotificationsWithDetailsUseCase(get())
    }
    single {
        GetUnreadNotificationsForProfessorUseCase(get())
    }
    single {
        GetUnreadNotificationsForStudentUseCase(get())
    }
    single {
        GetUnreadCountForProfessorUseCase(get())
    }
    single {
        GetUnreadCountForStudentUseCase(get())
    }
    single {
        UpdateNotificationReadableByProfessorUseCase(get())
    }
    single {
        UpdateNotificationReadableByStudentUseCase(get())
    }
    single {
        GetAllNotificationsForProfessorUseCase(get())
    }
    single {
        GetAllNotificationsForStudentUseCase(get())
    }
}