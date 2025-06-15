package com.example.edutasker.screens.professor.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.R
import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TaskStatus
import com.example.edutasker.mapper.taskDomainToTaskEntity
import com.example.edutasker.mapper.taskDomainToTasksWithStudentImageModel
import com.example.edutasker.model.ProfessorBasicModel
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorEvents
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorState
import com.example.edutasker.screens.professor.viewModel.stateAndEvents.ProfessorUiEvents
import com.example.edutasker.useCases.task.TaskUseCases
import com.example.edutasker.useCases.student.GetAllStudentsOfSpecificProfessorUseCase
import com.example.edutasker.useCases.professor.GetProfessorTitlesOfSubjectWhichAreSuitableIfExistSelectedStudentUseCase
import com.example.edutasker.useCases.student.GetNameIdsAndImageOfStudentUseCase
import com.example.edutasker.useCases.student.SearchStudentsUseCase
import com.example.edutasker.useCases.task.GetAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase
import com.example.edutasker.useCases.task.updateByProfessor.UpdateTaskByProfessorUseCase
import com.example.edutasker.useCases.notification.GetUnreadCountForProfessorUseCase
import com.example.edutasker.useCases.notification.GetAllNotificationsForProfessorUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByProfessorUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByStudentForTaskUseCase
import com.example.edutasker.useCases.notification.InsertNotificationUseCase
import com.example.edutasker.utils.DateHelper
import com.example.edutasker.utils.catchAndHandleError
import com.example.edutasker.utils.showErrorBasedErrorCode
import kotlinx.coroutines.CoroutineDispatcher
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
    private val getProfessorTitlesOfSubjectWhichAreSuitableIfExistSelectedStudentUseCase: GetProfessorTitlesOfSubjectWhichAreSuitableIfExistSelectedStudentUseCase,
    private val getNameIdsAndImageOfStudentUseCase: GetNameIdsAndImageOfStudentUseCase,
    private val searchStudentsUseCase: SearchStudentsUseCase,
    private val getAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase: GetAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase,
    private val updateTaskByProfessorUseCase: UpdateTaskByProfessorUseCase,
    private val getUnreadCountForProfessorUseCase: GetUnreadCountForProfessorUseCase,
    private val getAllNotificationsForProfessorUseCase: GetAllNotificationsForProfessorUseCase,
    private val updateNotificationReadableByProfessorUseCase: UpdateNotificationReadableByProfessorUseCase,
    private val updateNotificationReadableByStudentForTaskUseCase: UpdateNotificationReadableByStudentForTaskUseCase,
    private val insertNotificationUseCase: InsertNotificationUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _state = MutableStateFlow(ProfessorState())
    val state: StateFlow<ProfessorState> = _state

    private var job: Job? = null
    private var searchJob: Job? = null

    init {
        listenToLocalItems()
        onEvent(ProfessorEvents.Initialize)
        loadNotifications()
    }

    fun onEvent(event: ProfessorEvents) {
        when (event) {
            is ProfessorEvents.SelectedUnselectedStudentForAddingAssignment -> {
                _state.update {
                    it.copy(
                        selectedStudentForAddingAssignment = event.student
                    )
                }
            }

            is ProfessorEvents.UpdateTask -> {
                viewModelScope.launch {
                    flow { emit(updateTaskByProfessorUseCase(event.taskInfo)) }.catchAndHandleError { _, errorCode ->
                        handlingError(errorCode)
                    }.collect() {
                        updateNotificationForTaskStatusChange(event.taskInfo.taskId)
                        closeTaskDialogAndCleaningData()
                    }
                }
            }

            ProfessorEvents.CloseTaskDialog -> {
                closeTaskDialogAndCleaningData()
            }

            is ProfessorEvents.OpenTaskDialog -> {
                getInfoAboutOpenedTask(event.taskId)
            }

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

            is ProfessorEvents.SearchProfessorStudents -> {
                getStudentsByProfessorSubject(event.selectedSubjectOfTask)
            }

            is ProfessorEvents.AddTask -> {
                setAssignments(
                    event.title,
                    event.description,
                    event.deadline,
                    event.selectedUsers,
                    event.subjectName
                )
            }

            is ProfessorEvents.SelectStudentToSeeBacklog -> {
                getAllTasksBySpecificProfessorOfStudent(event.student)
                _state.update {
                    it.copy(
                        searchedStudents = listOf(),
                        selectedStudentFromSearch = event.student,
                        keyword = "",
                    )
                }
            }

            ProfessorEvents.OpenDialogToAddNewTask -> {
                _state.update {
                    it.copy(
                        isAddDialogVisible = true
                    )
                }
            }

            ProfessorEvents.OpenNotificationDialog -> {
                _state.update {
                    it.copy(
                        isNotificationDialogVisible = true
                    )
                }
                loadNotifications()
            }

            ProfessorEvents.CloseNotificationDialog -> {
                _state.update {
                    it.copy(
                        isNotificationDialogVisible = false
                    )
                }
            }

            is ProfessorEvents.MarkNotificationAsRead -> {
                markNotificationAsRead(event.notificationId)
            }

            ProfessorEvents.LoadNotifications -> {
                loadNotifications()
            }

            ProfessorEvents.Logout -> {
                CurrentUser.setCurrentUserNull()
                _state.update {
                    it.copy(
                        uiEvents = ProfessorUiEvents.GoToLogout
                    )
                }
            }

            is ProfessorEvents.GetAllTasksByStudent -> {

            }

            ProfessorEvents.Initialize -> {
                getInfoOfStudentsToSetRowImages()
            }

            else -> {}
        }
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            flow { emit(getUnreadCountForProfessorUseCase(CurrentUser.getCurrentUserId())) }
                .catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect { count ->
                    _state.update {
                        it.copy(unreadNotificationsCount = count)
                    }
                }

            flow { emit(getAllNotificationsForProfessorUseCase(CurrentUser.getCurrentUserId())) }
                .catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect { notifications ->
                    val unreadTaskIds = notifications
                        .filter { !it.taskDetails.progress.name.equals("DONE", true) }
                        .map { it.taskDetails.taskId }
                        .toSet()

                    _state.update {
                        it.copy(
                            notifications = notifications,
                            unreadTaskIds = unreadTaskIds
                        )
                    }
                }
        }
    }

    private fun markNotificationAsRead(notificationId: String) {
        viewModelScope.launch {
            flow { emit(updateNotificationReadableByProfessorUseCase(notificationId, true)) }
                .catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect {
                    loadNotifications()
                }
        }
    }

    private fun updateNotificationForTaskStatusChange(taskId: String) {
        viewModelScope.launch {
            flow { emit(updateNotificationReadableByStudentForTaskUseCase(taskId, false)) }
                .catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect {
                    loadNotifications()
                }
        }
    }

    private fun createNotificationForNewTask(taskId: String, studentId: String) {
        viewModelScope.launch {
            val notification = NotificationEntity(
                notificationId = "",
                taskId = taskId,
                studentId = studentId,
                professorId = CurrentUser.getCurrentUserId(),
                readableByProfessor = true,
                readableByStudent = false
            )

            flow { emit(insertNotificationUseCase(notification)) }
                .catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect {
                    loadNotifications()
                }
        }
    }

    private fun listenToLocalItems() {
        viewModelScope.launch {
            withContext(ioDispatcher) {
                if (state.value.selectedStudentFromSearch.studentId.isBlank()) {
                    taskUseCases.getAllTasksOfAllStudents().catchAndHandleError { _, errorCode ->
                        handlingError(errorCode)
                    }.collect() { res ->
                        _state.update { updateState ->
                            updateState.copy(
                                allTasksByEveryone = res.map { it.taskDomainToTasksWithStudentImageModel() },
                            )
                        }
                    }

                } else {
                    taskUseCases.getAllTasksBySpecificProfessorOfStudent(state.value.selectedStudentFromSearch.studentId)
                        .catchAndHandleError { _, errorCode ->
                            handlingError(errorCode)
                        }.collect() { res ->
                            _state.update { updateState ->
                                updateState.copy(
                                    allTasksByProfessorStudent = res.map {
                                        it.taskDomainToTasksWithStudentImageModel()
                                    }
                                )
                            }
                        }
                }
            }
        }
    }

    private fun getInfoAboutOpenedTask(taskId: String) {
        viewModelScope.launch {
            flow { emit(getAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase(taskId)) }.catchAndHandleError { _, errorCode ->
                _state.update {
                    it.copy(
                        isTaskOpened = false,
                        openedTask = OpenedTaskModel(
                            TaskModel(),
                            StudentPreviewAsListModel(),
                            ProfessorBasicModel()
                        ),
                        messageErrorId = errorCode.showErrorBasedErrorCode(),
                        uiEvents = ProfessorUiEvents.Error
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

    private fun getAllTasksBySpecificProfessorOfStudent(student: StudentPreviewAsListModel) {
        viewModelScope.launch {
            taskUseCases.getAllTasksBySpecificProfessorOfStudent(student.studentId)
                .catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect() { res ->
                    _state.update {
                        it.copy(
                            searchedStudents = listOf(),
                            selectedStudentFromSearch = student,
                            keyword = "",
                            allTasksByProfessorStudent = res.map { it.taskDomainToTasksWithStudentImageModel() }
                        )
                    }
                }
        }
    }

    private fun searchStudents(keyword: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
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
                        searchedStudents = if (keyword.isEmpty()) listOf() else res,
                        keyword = keyword
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

    private fun getStudentsByProfessorSubject(selectedSubjectOfTask: String) {
        job?.cancel()
        job = viewModelScope.launch {
            delay(400)
            withContext(Dispatchers.IO) {
                flow { emit(getAllStudentsOfSpecificProfessorUseCase(specificSubject = selectedSubjectOfTask)) }.catchAndHandleError { _, errorCode ->
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
            flow {
                emit(
                    getProfessorTitlesOfSubjectWhichAreSuitableIfExistSelectedStudentUseCase(
                        state.value.selectedStudentForAddingAssignment
                    )
                )
            }.catchAndHandleError { _, errorCode ->
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

    private fun setAssignments(
        taskTitle: String,
        description: String,
        deadline: String,
        selectedUsers: List<String>,
        subjectName: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val sizeOfStudents = selectedUsers.size
            var count: Int = 0

            selectedUsers.map { student ->
                flow {
                    val task = TaskModel(
                        taskId = "",
                        subjectName = subjectName,
                        description = description,
                        assignTo = student,
                        assignByProfessor = CurrentUser.getCurrentUserId(),
                        deadlineDate = deadline,
                        creationDate = DateHelper.getCurrentDate(),
                        progress = TaskStatus.TODO,
                        taskTitle = taskTitle

                    )
                    emit(taskUseCases.insertTask(task.taskDomainToTaskEntity()))
                }.catchAndHandleError { _, errorCode ->
                    handlingError(errorCode)
                }.collect { taskId ->
                    if (taskId.isNotEmpty()) {
                        createNotificationForNewTask(taskId, student)
                    }
                    count++
                    if (sizeOfStudents == count) {
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