package com.example.edutasker.screens.professor.viewModel.stateAndEvents

import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.UpdateTaskByProfessorModel

sealed class ProfessorEvents {
    data object WillingToAddTask : ProfessorEvents()
    data class AddTask(
        val title: String,
        val description: String,
        val selectedUsers: List<String>,
        val deadline: String,
        val subjectName: String,
    ) : ProfessorEvents()

    data class SearchStudents(val keyword: String) : ProfessorEvents()
    data object Logout : ProfessorEvents()
    data object OpenDialogToAddNewTask : ProfessorEvents()
    data object DismissAddTaskScreen : ProfessorEvents()
    data object GetSubjectsOfProfessor : ProfessorEvents()
    data class SearchProfessorStudents(val selectedSubjectOfTask: String) : ProfessorEvents()
    data class SelectedUnselectedStudentForAddingAssignment(val student: StudentPreviewAsListModel?) :
        ProfessorEvents()

    data object Initialize : ProfessorEvents()
    data object CloseTaskDialog : ProfessorEvents()
    data class OpenTaskDialog(val taskId: String) : ProfessorEvents()
    data class GetAllTasksByStudent(val studentId: String) : ProfessorEvents()
    data class UpdateTask(val taskInfo: UpdateTaskByProfessorModel) : ProfessorEvents()
    data class SelectStudentToSeeBacklog(val student: StudentPreviewAsListModel) : ProfessorEvents()
    data object OpenNotificationDialog : ProfessorEvents()
    data object CloseNotificationDialog : ProfessorEvents()
    data class MarkNotificationAsRead(val notificationId: String) : ProfessorEvents()
    data object LoadNotifications : ProfessorEvents()
    data object None : ProfessorEvents()
}

sealed class ProfessorUiEvents {
    data object GoToLogout : ProfessorUiEvents()
    data object None : ProfessorUiEvents()
    data object Success : ProfessorUiEvents()
    data object Error : ProfessorUiEvents()
}