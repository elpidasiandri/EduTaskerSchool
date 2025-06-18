package com.example.edutasker.di

import com.example.edutasker.repo.notificationDatabase.view.INotificationDatabaseRepo
import com.example.edutasker.repo.notificationDatabase.view.NotificationDatabaseRepoImpl
import com.example.edutasker.screens.notification.viewModel.NotificationViewModel
import com.example.edutasker.useCases.notification.view.GetAllNotificationsForProfessorUseCase
import com.example.edutasker.useCases.notification.view.GetAllNotificationsForStudentUseCase
import com.example.edutasker.useCases.notification.view.UpdateNotificationReadableByProfessorUseCase
import com.example.edutasker.useCases.notification.view.UpdateNotificationReadableByStudentUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notificationActivityModule = module {
    viewModel {
        NotificationViewModel(get(), get(), get(), get())
    }
    single<INotificationDatabaseRepo> {
        NotificationDatabaseRepoImpl(get())
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