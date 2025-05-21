package com.example.edutasker.di

import com.example.edutasker.screens.professor.viewModel.ProfessorViewModel
import com.example.edutasker.useCases.student.GetAllStudentsOfSpecificProfessorUseCase
import com.example.edutasker.useCases.professor.GetProfessorTitlesOfSubjectUseCase
import com.example.edutasker.useCases.student.GetNameIdsAndImageOfStudentUseCase
import com.example.edutasker.useCases.student.SearchProfessorStudentsHavingSpecificSubjectUseCase
import com.example.edutasker.useCases.student.SearchStudentsUseCase
import com.example.edutasker.useCases.task.GetAllInfoOfTaskAndBasicOfStudentsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val professorModule = module {
    viewModel {
        ProfessorViewModel(get(), get(), get(), get(), get(), get())
    }
    single {
        GetAllStudentsOfSpecificProfessorUseCase(get())
    }
    single {
        SearchProfessorStudentsHavingSpecificSubjectUseCase(get())
    }
    single {
        GetNameIdsAndImageOfStudentUseCase(get())
    }
    single {
        GetProfessorTitlesOfSubjectUseCase(get())
    }
    single {
        SearchStudentsUseCase(get())
    }
    single {
        GetAllInfoOfTaskAndBasicOfStudentsUseCase(get())
    }
}