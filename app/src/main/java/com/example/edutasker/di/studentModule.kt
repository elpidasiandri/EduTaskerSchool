package com.example.edutasker.di

import com.example.edutasker.screens.student.viewModel.StudentViewModel
import com.example.edutasker.useCases.student.GetNameIdsAndImageOfStudentUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val studentModule = module {
    viewModel {
        StudentViewModel(get(), get())
    }
    single {
        GetNameIdsAndImageOfStudentUseCase(get())
    }
}