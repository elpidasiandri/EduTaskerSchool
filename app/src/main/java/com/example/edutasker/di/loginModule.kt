package com.example.edutasker.di

import com.example.edutasker.screens.login.viewModelState.LoginScreenViewModel
import com.example.edutasker.useCases.professor.GetProfessorByEmailUseCase
import com.example.edutasker.useCases.professor.LoginProfessorUseCase
import com.example.edutasker.useCases.student.GetStudentByEmailUseCase
import com.example.edutasker.useCases.student.LoginStudentUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginScreenViewModel(get(), get(), get(), get())
    }
    single {
        LoginProfessorUseCase(get())
    }
    single {
        LoginStudentUseCase(get())
    }
    single {
        GetProfessorByEmailUseCase(get())
    }
    single {
        GetStudentByEmailUseCase(get())
    }
}