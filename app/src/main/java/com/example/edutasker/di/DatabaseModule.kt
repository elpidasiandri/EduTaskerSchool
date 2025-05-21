package com.example.edutasker.di

import androidx.room.Room
import com.example.edutasker.dao.EduTaskerDatabase
import com.example.edutasker.repo.DatabaseRepositoryImpl
import com.example.edutasker.repo.IDatabaseRepository
import com.example.edutasker.useCases.task.needOnInitialize.GetTaskCountByProfessorUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetTaskCountPerSubjectUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetTasksByProfessorUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetTasksForSubjectUseCase
import com.example.edutasker.useCases.professor.InsertProfessorUseCase
import com.example.edutasker.useCases.student.InsertStudentUseCase
import com.example.edutasker.useCases.task.needOnInitialize.InsertTaskUseCase
import com.example.edutasker.useCases.task.TaskUseCases
import com.example.edutasker.useCases.professor.GetProfessorByEmailUseCase
import com.example.edutasker.useCases.student.GetStudentByEmailUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksBySpecificProfessorOfStudentUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksOfAllStudents
import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksOfProfessorStudentUseCase
import com.example.edutasker.useCases.task.needOnInitialize.InsertMockDataUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            EduTaskerDatabase::class.java, "database-name"
        ).build()
    }
    single<IDatabaseRepository> {
        DatabaseRepositoryImpl(
            get(),
            get(),
            get(),
            get(),
        )
    }
    single<CoroutineDispatcher> { Dispatchers.IO }

    single {
        get<EduTaskerDatabase>().taskDao()
    }
    single {
        get<EduTaskerDatabase>().studentDao()
    }
    single {
        get<EduTaskerDatabase>().professorDao()
    }
    single {
        InsertStudentUseCase(get())
    }
    single {
        InsertProfessorUseCase(get())
    }
    single {
        GetStudentByEmailUseCase(get())
    }
    single {
        GetProfessorByEmailUseCase(get())
    }
    single {
        TaskUseCases(
            insertTask = InsertTaskUseCase(get()),
            getTaskCountByProfessor = GetTaskCountByProfessorUseCase(get()),
            getTaskCountPerSubject = GetTaskCountPerSubjectUseCase(get()),
            getTasksByProfessor = GetTasksByProfessorUseCase(get()),
            getTasksForSubject = GetTasksForSubjectUseCase(get()),
            getAllTasksOfAllStudents = GetAllTasksOfAllStudents(get()),
            getAllTasksOfProfessorStudent = GetAllTasksOfProfessorStudentUseCase(get()),
            getAllTasksBySpecificProfessorOfStudent = GetAllTasksBySpecificProfessorOfStudentUseCase(get()),
            insertMockData = InsertMockDataUseCase(get())
        )
    }
}