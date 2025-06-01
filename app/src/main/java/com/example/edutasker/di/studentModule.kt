package com.example.edutasker.di

import com.example.edutasker.screens.student.viewModel.StudentViewModel
import com.example.edutasker.useCases.student.GetNameIdsAndImageOfStudentUseCase
import com.example.edutasker.useCases.task.GetAllTasksByStudentIdUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val studentModule = module {
    viewModel {
        StudentViewModel(get(), get(), get())
    }
    single {
        GetAllTasksByStudentIdUseCase(get())
    }
    single<CoroutineDispatcher> { Dispatchers.IO }
}