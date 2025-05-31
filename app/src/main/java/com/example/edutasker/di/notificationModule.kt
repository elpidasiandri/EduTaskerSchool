package com.example.edutasker.di

import com.example.edutasker.useCases.notification.GetAllNotificationsWithDetailsUseCase
import com.example.edutasker.useCases.notification.InsertNotificationUseCase
import org.koin.dsl.module

val notificationModule = module {
    single {
        GetAllNotificationsWithDetailsUseCase(get())
    }
    single {
        InsertNotificationUseCase(get())
    }
}