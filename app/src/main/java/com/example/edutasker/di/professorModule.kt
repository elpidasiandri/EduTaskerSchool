package com.example.edutasker.di

import com.example.edutasker.screens.professor.viewModel.ProfessorViewModel
import com.example.edutasker.useCases.student.GetAllStudentsOfSpecificProfessorUseCase
import com.example.edutasker.useCases.professor.GetProfessorTitlesOfSubjectWhichAreSuitableIfExistSelectedStudentUseCase
import com.example.edutasker.useCases.student.GetNameIdsAndImageOfStudentUseCase
import com.example.edutasker.useCases.student.SearchStudentsUseCase
import com.example.edutasker.useCases.task.GetAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase
import com.example.edutasker.useCases.task.updateByProfessor.UpdateTaskByProfessorUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val professorModule = module {
    viewModel {
        ProfessorViewModel(
            get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get(), get()
        )
    }
    single {
        GetAllStudentsOfSpecificProfessorUseCase(get())
    }
    single {
        GetNameIdsAndImageOfStudentUseCase(get())
    }
    single {
        GetProfessorTitlesOfSubjectWhichAreSuitableIfExistSelectedStudentUseCase(get())
    }
    single {
        SearchStudentsUseCase(get())
    }
    single {
        GetAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase(get())
    }
    single {
        UpdateTaskByProfessorUseCase(get())
    }
    single<CoroutineDispatcher> { Dispatchers.IO }
}