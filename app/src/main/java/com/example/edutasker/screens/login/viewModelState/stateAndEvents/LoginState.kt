package com.example.edutasker.screens.login.viewModelState.stateAndEvents

data class LoginState(
    val password: String = "",
    val email: String = "",
    val isStudent: Boolean = false,
    val messageErrorId: Int = -1,
    val events: LoginEvents = LoginEvents.None,
    val uiEvents: LoginUiEvents = LoginUiEvents.None,
)