package com.example.edutasker.screens.student.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.mapper.toTaskModel
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.ProfessorBasicModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.UpdateTaskByProfessorModel
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentEvents
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentState
import com.example.edutasker.screens.student.viewModel.stateAndEvents.StudentUiEvents
import com.example.edutasker.useCases.notification.GetUnreadCountForStudentUseCase
import com.example.edutasker.useCases.task.GetAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase
import com.example.edutasker.useCases.task.GetAllTasksByStudentIdUseCase
import com.example.edutasker.useCases.task.updateByStudent.UpdateTaskByStudentUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByProfessorForTaskUseCase
import com.example.edutasker.utils.catchAndHandleError
import com.example.edutasker.utils.showErrorBasedErrorCode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentViewModel(
    private val getAllTasksByStudentIdUseCase: GetAllTasksByStudentIdUseCase,
    private val ioDispatcher: CoroutineDispatcher,
    private val getAllInfoOfOpenedTaskUseCase: GetAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase,
    private val updateTaskByStudentUseCase: UpdateTaskByStudentUseCase,
    private val getUnreadCountForStudentUseCase: GetUnreadCountForStudentUseCase,
    private val updateNotificationReadableByProfessorForTaskUseCase: UpdateNotificationReadableByProfessorForTaskUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(StudentState())
    val state: StateFlow<StudentState> = _state

    init {
        getLocalTasksOfStudent()
        loadUnreadNotifications()
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
                getInfoAboutOpenedTask(event.taskId)
            }

            StudentEvents.CloseTaskDialog -> {
                closeTaskDialogAndCleaningData()
            }

            is StudentEvents.UpdateTask -> {
                updateProgressStatusOfTask(event.taskInfo)
            }

            StudentEvents.OpenNotification -> {
                _state.update {
                    it.copy(
                        uiEvents = StudentUiEvents.OpenNotification
                    )
                }
            }

            else -> {}
        }
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

    private fun loadUnreadNotifications() {
        viewModelScope.launch {
            getUnreadCountForStudentUseCase(CurrentUser.getCurrentUserId())
                .catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect { count ->
                    _state.update {
                        it.copy(unreadNotificationsCount = count)
                    }
                }
        }
    }

    private fun updateNotificationForTaskStatusChange(taskId: String) {
        viewModelScope.launch {
            flow { emit(updateNotificationReadableByProfessorForTaskUseCase(taskId, false)) }
                .catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect {}
        }
    }

    private fun updateProgressStatusOfTask(model: UpdateTaskByProfessorModel) {
        viewModelScope.launch {
            flow { emit(updateTaskByStudentUseCase(model)) }.catchAndHandleError { _, errorCode ->
                handlingError(errorCode)
            }.collect() {
                updateNotificationForTaskStatusChange(model.taskId)
                closeTaskDialogAndCleaningData()
            }
        }
    }

    private fun getInfoAboutOpenedTask(taskId: String) {
        viewModelScope.launch {
            flow { emit(getAllInfoOfOpenedTaskUseCase(taskId)) }.catchAndHandleError { _, errorCode ->
                _state.update {
                    it.copy(
                        isTaskOpened = false,
                        openedTask = OpenedTaskModel(
                            TaskModel(),
                            StudentPreviewAsListModel(),
                            ProfessorBasicModel()
                        ),
                        messageErrorId = errorCode.showErrorBasedErrorCode(),
                        uiEvents = StudentUiEvents.Error
                    )
                }
            }.collect { taskAndStudentInfo ->
                val taskInfo = taskAndStudentInfo.taskInfo
                val studentInfo = taskAndStudentInfo.studentBasic
                val professorInfo = taskAndStudentInfo.professorBasic
                _state.update {
                    it.copy(
                        isTaskOpened = true,
                        openedTask = OpenedTaskModel(
                            TaskModel(
                                taskId = taskInfo.taskId,
                                taskTitle = taskInfo.taskTitle,
                                subjectName = taskInfo.subjectName,
                                description = taskInfo.description,
                                assignTo = taskInfo.assignTo,
                                assignByProfessor = taskInfo.assignByProfessor,
                                deadlineDate = taskInfo.deadlineDate,
                                creationDate = taskInfo.creationDate,
                                progress = taskInfo.progress

                            ),
                            StudentPreviewAsListModel(
                                studentId = studentInfo.studentId,
                                username = studentInfo.username,
                                image = studentInfo.image
                            ),
                            ProfessorBasicModel(
                                id = professorInfo.id,
                                username = professorInfo.username,
                                image = professorInfo.image
                            )
                        )
                    )
                }
            }
        }
    }

    private fun closeTaskDialogAndCleaningData() {
        _state.update {
            it.copy(
                isTaskOpened = false,
                openedTask = OpenedTaskModel(
                    TaskModel(),
                    StudentPreviewAsListModel(),
                    ProfessorBasicModel()
                )
            )
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