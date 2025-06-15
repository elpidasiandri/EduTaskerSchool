package com.example.edutasker.di

import androidx.room.Room
import com.example.edutasker.db.dao.EduTaskerDatabase
import com.example.edutasker.repo.notificationDatabase.INotificationDatabaseRepo
import com.example.edutasker.repo.notificationDatabase.NotificationDatabaseRepoImpl
import com.example.edutasker.repo.relationsDatabase.DatabaseRepositoryImpl
import com.example.edutasker.repo.relationsDatabase.IDatabaseRepository
import com.example.edutasker.repo.professorDatabase.IProfessorDatabaseRepository
import com.example.edutasker.repo.professorDatabase.ProfessorDatabaseRepositoryImpl
import com.example.edutasker.repo.studentDatabase.IStudentDatabaseRepository
import com.example.edutasker.repo.studentDatabase.StudentDatabaseRepositoryImpl
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository
import com.example.edutasker.repo.taskDatabase.TaskDatabaseRepositoryImpl
import com.example.edutasker.useCases.professor.InsertProfessorUseCase
import com.example.edutasker.useCases.student.InsertStudentUseCase
import com.example.edutasker.useCases.task.needOnInitialize.InsertTaskUseCase
import com.example.edutasker.useCases.task.TaskUseCases
import com.example.edutasker.useCases.professor.GetProfessorByEmailUseCase
import com.example.edutasker.useCases.student.GetStudentByEmailUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksBySpecificProfessorOfStudentUseCase
import com.example.edutasker.useCases.task.needOnInitialize.GetAllTasksOfAllStudents
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
        )
    }
    single<ITaskDatabaseRepository> {
        TaskDatabaseRepositoryImpl(
            get(),
            get(),
        )
    }
    single<IStudentDatabaseRepository> {
        StudentDatabaseRepositoryImpl(get())
    }
    single<INotificationDatabaseRepo> {
        NotificationDatabaseRepoImpl(get())
    }
    single<IProfessorDatabaseRepository> {
        ProfessorDatabaseRepositoryImpl(get(), get())
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
        get<EduTaskerDatabase>().notificationDao()
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
            getAllTasksOfAllStudents = GetAllTasksOfAllStudents(get()),
            getAllTasksBySpecificProfessorOfStudent = GetAllTasksBySpecificProfessorOfStudentUseCase(
                get()
            ),
            insertMockData = InsertMockDataUseCase(get(), get(), get(), get(), get())
        )
    }
    includes(notificationDatabaseModule)
}