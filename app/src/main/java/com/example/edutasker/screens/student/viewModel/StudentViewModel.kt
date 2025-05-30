package com.example.edutasker.screens.student.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorUiEvents
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentEvents
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentState
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentUiEvents
import com.example.edutasker.useCases.student.GetNameIdsAndImageOfStudentUseCase
import com.example.edutasker.useCases.task.TaskUseCases
import com.example.edutasker.utils.catchAndHandleError
import com.example.edutasker.utils.showErrorBasedErrorCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudentViewModel(
    private val getNameIdsAndImageOfStudentUseCase: GetNameIdsAndImageOfStudentUseCase,
    private val taskUseCases: TaskUseCases,

    ) : ViewModel() {
    private val _state = MutableStateFlow(StudentState())
    val state: StateFlow<StudentState> = _state

    init {
        onEvent(StudentEvents.Initialize)
    }

    fun onEvent(event: StudentEvents) {
        when (event) {
            StudentEvents.Initialize -> {
                getInfoOfStudentsToSetRowImages()
                getAllTasksByEveryone()
            }

            else -> {}
        }
    }

    private fun getInfoOfStudentsToSetRowImages() {
        viewModelScope.launch {
            flow { emit(getNameIdsAndImageOfStudentUseCase()) }.catchAndHandleError { errorMessage, errorCode ->
                handlingError(errorCode)
            }.collect() { res ->
                _state.update {
                    it.copy(
                        studentsToAppearOnCentralRow = res.map { student ->
                            StudentPreviewAsListModel(
                                studentId = student.studentId,
                                username = student.username,
                                image = student.image
                            )
                        }
                    )
                }
            }
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

    private fun getAllTasksByEveryone() {
        viewModelScope.launch {
            flow {
                emit(
                    taskUseCases.getAllTasksOfAllStudents()
                )
            }.catchAndHandleError { errorMessage, errorCode ->
                handlingError(errorCode)
            }
                .collect { res ->
                    _state.update {
                        it.copy(
                            allTasksByEveryone = res
                        )
                    }
                }

        }
    }

}