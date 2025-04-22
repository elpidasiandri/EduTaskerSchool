package com.example.edutasker.di

import androidx.room.Room
import com.example.edutasker.dao.EduTaskerDatabase
import com.example.edutasker.repo.DatabaseRepositoryImpl
import com.example.edutasker.repo.IDatabaseRepository
import com.example.edutasker.useCases.task.AssignTaskToStudentsUseCase
import com.example.edutasker.useCases.task.GetTaskCountByProfessorUseCase
import com.example.edutasker.useCases.task.GetTaskCountPerSubjectUseCase
import com.example.edutasker.useCases.task.GetTasksByProfessorUseCase
import com.example.edutasker.useCases.task.GetTasksForStudentUseCase
import com.example.edutasker.useCases.task.GetTasksForSubjectUseCase
import com.example.edutasker.useCases.professor.InsertProfessorUseCase
import com.example.edutasker.useCases.student.InsertStudentUseCase
import com.example.edutasker.useCases.task.InsertTaskUseCase
import com.example.edutasker.useCases.TaskUseCases
import com.example.edutasker.useCases.professor.GetProfessorByEmailUseCase
import com.example.edutasker.useCases.student.GetStudentByEmailUseCase
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
            get()
        )
    }

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
        TaskUseCases(
            insertStudent = InsertStudentUseCase(get()),
            insertProfessor = InsertProfessorUseCase(get()),
            insertTask = InsertTaskUseCase(get()),
            assignTaskToStudents = AssignTaskToStudentsUseCase(get()),
            getTasksForStudent = GetTasksForStudentUseCase(get()),
            getTaskCountByProfessor = GetTaskCountByProfessorUseCase(get()),
            getTaskCountPerSubject = GetTaskCountPerSubjectUseCase(get()),
            getTasksByProfessor = GetTasksByProfessorUseCase(get()),
            getTasksForSubject = GetTasksForSubjectUseCase(get()),
            getStudentByEmail  = GetStudentByEmailUseCase(get()),
            getProfessorByEmail  = GetProfessorByEmailUseCase(get()),
        )
    }

}