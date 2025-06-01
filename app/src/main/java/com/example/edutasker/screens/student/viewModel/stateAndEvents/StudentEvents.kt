package com.example.edutasker.screens.student.viewModel.stateAndEvents

sealed class StudentEvents {
    data object None : StudentEvents()
    data object Logout : StudentEvents()
    data class OpenTaskDialog(val taskId: String) : StudentEvents()
}

sealed class StudentUiEvents {
    data object GoToLogout : StudentUiEvents()
    data object None : StudentUiEvents()
    data object Success : StudentUiEvents()
    data object Error : StudentUiEvents()
}
