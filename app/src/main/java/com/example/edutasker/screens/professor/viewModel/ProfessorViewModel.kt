package com.example.edutasker.screens.professor.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.model.TaskModel
import com.example.edutasker.screens.professor.mapper.taskDomainToTaskEntity
import com.example.edutasker.useCases.TaskUseCases
import com.example.edutasker.utils.catchAndHandleError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ProfessorViewModel(
    private val taskUseCases: TaskUseCases,
) : ViewModel() {

    fun test(task: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            flow {
                emit(taskUseCases.insertTask(task.taskDomainToTaskEntity()))
            }.catchAndHandleError { errorMessage, errorCode ->
                Log.d("Q12345", " errorMessage ${errorMessage} errorCode $errorCode")
            }.collect { info ->
                Log.d("Q12345", " info ${info} ")

            }
        }
    }
}