package com.example.edutasker.screens.notification.viewModel.viewModelAndStaet

sealed class NotificationEvents {
    data object None : NotificationEvents()
    data class MarkNotificationAsRead(val notificationId: String) : NotificationEvents()
}

sealed class NotificationEventsUI {
    data object None : NotificationEventsUI()
    data object Exit : NotificationEventsUI()
}