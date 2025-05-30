package com.example.edutasker

import android.app.Application
import com.example.edutasker.di.databaseModule
import com.example.edutasker.mockData.MockData
import com.example.edutasker.useCases.task.TaskUseCases
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.java.KoinJavaComponent.getKoin
import timber.log.Timber

class EduTaskerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EduTaskerApp)
            modules(databaseModule)
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
            val taskUseCases: TaskUseCases = getKoin().get()
            MockData.insertMockData(
                taskUseCases
            )
        }
    }
}
