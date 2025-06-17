package com.example.edutasker.screens.notification.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.screens.notification.viewModel.viewModelAndStaet.NotificationEvents
import com.example.edutasker.screens.notification.viewModel.viewModelAndStaet.NotificationState
import com.example.edutasker.useCases.notification.GetAllNotificationsForProfessorUseCase
import com.example.edutasker.useCases.notification.GetAllNotificationsForStudentUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByProfessorUseCase
import com.example.edutasker.useCases.notification.UpdateNotificationReadableByStudentUseCase
import com.example.edutasker.utils.catchAndHandleError
import com.example.edutasker.utils.showErrorBasedErrorCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val getAllNotificationsForProfessorUseCase: GetAllNotificationsForProfessorUseCase,
    private val getAllNotificationsForStudentUseCase: GetAllNotificationsForStudentUseCase,
    private val updateNotificationReadableByStudentUseCase: UpdateNotificationReadableByStudentUseCase,
    private val updateNotificationReadableByProfessorUseCase: UpdateNotificationReadableByProfessorUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(NotificationState())
    val state: StateFlow<NotificationState> = _state

    init {
        loadNotifications()
    }

    fun onEvent(event: NotificationEvents) {
        when (event) {
            is NotificationEvents.MarkNotificationAsRead -> {
                markNotificationAsRead(event.notificationId)
            }

            else -> {}
        }
    }

    private fun markNotificationAsRead(notificationId: String) {
        viewModelScope.launch {
            if (CurrentUser.getCurrentUserIfIsStudent()) {
                flow { emit(updateNotificationReadableByStudentUseCase(notificationId, true)) }
                    .catchAndHandleError { _, errorCode ->
                        handlingError(errorCode)
                    }.collect {}
            } else {
                flow { emit(updateNotificationReadableByProfessorUseCase(notificationId, true)) }
                    .catchAndHandleError { _, errorCode ->
                        handlingError(errorCode)
                    }.collect {
                    }
            }
        }
    }

    private fun loadNotifications() {
        viewModelScope.launch {
            if (CurrentUser.getCurrentUserIfIsStudent()) {
                flow { emit(getAllNotificationsForStudentUseCase(CurrentUser.getCurrentUserId())) }
                    .catchAndHandleError { _, errorCode ->
                        handlingError(errorCode)
                    }.collect { notifications ->
                        _state.update {
                            it.copy(notifications = notifications)
                        }
                    }
            } else {
                flow { emit(getAllNotificationsForProfessorUseCase(CurrentUser.getCurrentUserId())) }
                    .catchAndHandleError { _, errorCode ->
                        handlingError(errorCode)
                    }.collect { notifications ->
                        _state.update {
                            it.copy(
                                notifications = notifications,
                            )
                        }
                    }
            }

        }
    }

    fun initializeMessage() {
        _state.update {
            it.copy(
                messageErrorId = -1
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
            )
        }
    }
}