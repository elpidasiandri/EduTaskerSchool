package com.example.edutasker.di

import com.example.edutasker.screens.notification.viewModel.NotificationViewModel
import com.example.edutasker.useCases.notification.GetAllNotificationsForProfessorUseCase
import com.example.edutasker.useCases.notification.GetAllNotificationsForStudentUseCase
import com.example.edutasker.useCases.notification.GetUnreadNotificationsForProfessorUseCase
import com.example.edutasker.useCases.notification.GetUnreadNotificationsForStudentUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByProfessorUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByStudentUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notificationActivityModule = module {
    viewModel {
        NotificationViewModel(get(), get(), get(), get())
    }

    single {
        GetUnreadNotificationsForProfessorUseCase(get())
    }
    single {
        GetUnreadNotificationsForStudentUseCase(get())
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