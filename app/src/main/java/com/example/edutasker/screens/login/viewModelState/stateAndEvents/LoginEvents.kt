package com.example.edutasker.screens.login.viewModelState.stateAndEvents

sealed class LoginEvents {
    data object None : LoginEvents()
    data class GoToLogin(val isStudent: Boolean) : LoginEvents()
    data class TryToConnect(val email: String, val password: String) : LoginEvents()
}


sealed class LoginUiEvents {
    data object GoToLoginScreen : LoginUiEvents()
    data object None : LoginUiEvents()
    data object ErrorToast : LoginUiEvents()
    data object GoToProfessorScreen : LoginUiEvents()
    data object GoToStudentScreen : LoginUiEvents()
}