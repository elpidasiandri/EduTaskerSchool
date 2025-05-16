package com.example.edutasker.screens.professor.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.R
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.screens.professor.mapper.taskDomainToTaskEntity
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorEvents
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorState
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorUiEvents
import com.example.edutasker.useCases.task.TaskUseCases
import com.example.edutasker.useCases.student.GetAllStudentsOfSpecificProfessorUseCase
import com.example.edutasker.useCases.professor.GetProfessorTitlesOfSubjectUseCase
import com.example.edutasker.useCases.student.GetNameIdsAndImageOfStudentUseCase
import com.example.edutasker.useCases.student.SearchStudentsUseCase
import com.example.edutasker.utils.DateHelper
import com.example.edutasker.utils.catchAndHandleError
import com.example.edutasker.utils.showErrorBasedErrorCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfessorViewModel(
    private val taskUseCases: TaskUseCases,
    private val getAllStudentsOfSpecificProfessorUseCase: GetAllStudentsOfSpecificProfessorUseCase,
    private val getProfessorTitlesOfSubjectUseCase: GetProfessorTitlesOfSubjectUseCase,
    private val getNameIdsAndImageOfStudentUseCase: GetNameIdsAndImageOfStudentUseCase,
    private val searchStudentsUseCase: SearchStudentsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ProfessorState())
    val state: StateFlow<ProfessorState> = _state

    private var job: Job? = null

    init {
        onEvent(ProfessorEvents.LoadStudentsForArrow)
    }

    fun onEvent(event: ProfessorEvents) {
        when (event) {
            is ProfessorEvents.WillingToAddTask -> {
                setAddDialogVisibleFlag(true)
            }

            is ProfessorEvents.SearchStudents -> {
                searchStudents(event.keyword)
            }

            is ProfessorEvents.DismissAddTaskScreen -> {
                setAddDialogVisibleFlag(false)
            }

            ProfessorEvents.GetSubjectsOfProfessor -> {
                getSubjectsOfProfessor()
            }

            ProfessorEvents.SearchProfessorStudents -> {
                getStudentsByProfessorSubject()
            }

            is ProfessorEvents.AddTask -> {
                setAssignments(
                    event.description,
                    event.deadline,
                    event.selectedUsers,
                    event.subjectName
                )
            }

            ProfessorEvents.OpenDialogToAddNewTask -> {
                _state.update {
                    it.copy(
                        isAddDialogVisible = true
                    )
                }
            }

            ProfessorEvents.Logout -> {
                _state.update {
                    it.copy(
                        uiEvents = ProfessorUiEvents.GoToLogout
                    )
                }
            }

            ProfessorEvents.LoadStudentsForArrow -> {
                getInfoOfStudentsToSetRowImages()
            }

            else -> {}
        }
    }

    private fun searchStudents(keyword: String) {
        viewModelScope.launch {
            flow { emit(searchStudentsUseCase(keyword)) }.catchAndHandleError { _, errorCode ->
                _state.update {
                    it.copy(
                        messageErrorId = errorCode.showErrorBasedErrorCode(),
                        uiEvents = ProfessorUiEvents.Error,
                        searchedStudents = listOf()
                    )
                }
            }.collect() { res ->
                _state.update {
                    it.copy(
                        searchedStudents = if (keyword.isEmpty()) listOf() else res
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

    private fun getStudentsByProfessorSubject() {
        job?.cancel()
        job = viewModelScope.launch {
            delay(400)
            withContext(Dispatchers.IO) {
                flow { emit(getAllStudentsOfSpecificProfessorUseCase(specificSubject = null)) }.catchAndHandleError { errorMessage, errorCode ->
                    _state.update {
                        it.copy(
                            searchedStudentsForAssignment = listOf(),
                            uiEvents = ProfessorUiEvents.Error,
                            messageErrorId = errorCode.showErrorBasedErrorCode()
                        )
                    }
                }.collect() { res ->
                    _state.update {
                        it.copy(
                            searchedStudentsForAssignment = res
                        )
                    }
                }
            }
        }
    }

    private fun getSubjectsOfProfessor() {
        viewModelScope.launch(Dispatchers.IO) {
            flow { emit(getProfessorTitlesOfSubjectUseCase()) }.catchAndHandleError { _, errorCode ->
                _state.update {
                    it.copy(
                        professorSubjects = listOf(),
                        uiEvents = ProfessorUiEvents.Error,
                        messageErrorId = errorCode.showErrorBasedErrorCode()
                    )
                }
            }.collect() { res ->
                _state.update {
                    it.copy(
                        professorSubjects = res
                    )
                }
            }
        }
    }

    private fun setAddDialogVisibleFlag(flag: Boolean) {
        _state.update {
            it.copy(
                isAddDialogVisible = flag
            )
        }
    }

    fun setEventNone() {
        _state.update {
            it.copy(
                uiEvents = ProfessorUiEvents.None
            )
        }
    }

    private fun setAssignments(
        description: String,
        deadline: String,
        selectedUsers: List<String>,
        subjectName: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            flow {
                val task = TaskModel(
                    taskId = "",
                    subjectName = subjectName,
                    description = description,
                    assignTo = selectedUsers,
                    assignByProfessor = CurrentUser.userId ?: "",
                    deadlineDate = deadline,
                    creationDate = DateHelper.getCurrentDate(),
                    progress = TaskStatus.TODO

                )
                emit(taskUseCases.insertTask(task.taskDomainToTaskEntity()))
            }.catchAndHandleError { _, errorCode ->
                handlingError(errorCode)
            }.collect {
                _state.update {
                    it.copy(
                        uiEvents = ProfessorUiEvents.Success,
                        messageErrorId = R.string.success_new_task,
                        isAddDialogVisible = false
                    )
                }
            }
        }
    }

    private fun showError(messageInt: Int) {
        _state.update {
            it.copy(
                messageErrorId = messageInt,
                uiEvents = ProfessorUiEvents.Error
            )
        }
    }
}