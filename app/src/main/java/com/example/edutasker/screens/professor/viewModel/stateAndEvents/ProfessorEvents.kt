package com.example.edutasker.screens.professor.viewModel.stateAndEvents

sealed class ProfessorEvents {
    data object WillingToAddTask : ProfessorEvents()
    data class AddTask(
        val description: String,
        val selectedUsers: List<String>,
        val deadline: String,
        val subjectName: String,
    ) : ProfessorEvents()

    data object Logout : ProfessorEvents()
    data object DismissAddTaskScreen : ProfessorEvents()
    data object GetSubjectsOfProfessor : ProfessorEvents()
    data object SearchProfessorStudents : ProfessorEvents()
    data object None : ProfessorEvents()
}

sealed class ProfessorUiEvents {
    data object GoToLogout : ProfessorUiEvents()
    data object None : ProfessorUiEvents()
    data object Success : ProfessorUiEvents()
    data object Error : ProfessorUiEvents()

}
