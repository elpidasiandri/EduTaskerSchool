package com.example.edutasker.screens.notification.viewModel.viewModelAndStaet

import com.example.edutasker.model.NotificationsDetails

data class NotificationState(
    val events: NotificationEvents = NotificationEvents.None,
    val uiEvents: NotificationEventsUI = NotificationEventsUI.None,
    val messageErrorId: Int = -1,
    val notifications: List<NotificationsDetails> = listOf(),
    val unreadNotificationsCount: Int = 0
)