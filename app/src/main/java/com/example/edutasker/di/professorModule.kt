package com.example.edutasker.di

import com.example.edutasker.screens.professor.viewModel.ProfessorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val professorModule = module{
    viewModel {
        ProfessorViewModel(get())
    }
}