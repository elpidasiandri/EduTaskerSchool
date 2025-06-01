package com.example.edutasker.screens.student.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.mapper.toTaskModel
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.TaskModel
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentEvents
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentState
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentUiEvents
import com.example.edutasker.useCases.task.GetAllTasksByStudentIdUseCase
import com.example.edutasker.useCases.task.TaskUseCases
import com.example.edutasker.utils.catchAndHandleError
import com.example.edutasker.utils.showErrorBasedErrorCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewModel(
    private val taskUseCases: TaskUseCases,
    private val getAllTasksByStudentIdUseCase: GetAllTasksByStudentIdUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _state = MutableStateFlow(StudentState())
    val state: StateFlow<StudentState> = _state

    init {
        getLocalTasksOfStudent()
    }

    private fun getLocalTasksOfStudent() {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                getAllTasksByStudentIdUseCase(CurrentUser.getCurrentUserId()).catchAndHandleError { errorMessage, errorCode ->
                    _state.update {
                        it.copy(
                            allTasks = listOf(),
                            messageErrorId = errorCode.showErrorBasedErrorCode(),
                            uiEvents = StudentUiEvents.Error
                        )
                    }
                }.collect { res ->
                    val mappedTasks: List<TaskModel> = res
                        ?.filterNotNull()
                        ?.map { it.toTaskModel() }
                        ?: emptyList()

                    _state.update {
                        it.copy(allTasks = mappedTasks)
                    }
                }
            }
        }
    }

    fun onEvent(event: StudentEvents) {
        when (event) {
            StudentEvents.Logout -> {
                CurrentUser.setCurrentUserNull()
                _state.update {
                    it.copy(
                        uiEvents = StudentUiEvents.GoToLogout
                    )
                }
            }
            is StudentEvents.OpenTaskDialog -> {
            }
            else -> {}
        }
    }

    private fun handlingError(errorCode: Int) {
        showError(
            errorCode.showErrorBasedErrorCode()
        )
    }

    private fun showError(messageInt: Int) {
        _state.update {
            it.copy(
                messageErrorId = messageInt,
                uiEvents = StudentUiEvents.Error
            )
        }
    }

    fun setEventNone() {
        _state.update {
            it.copy(
                uiEvents = StudentUiEvents.None
            )
        }
    }
}