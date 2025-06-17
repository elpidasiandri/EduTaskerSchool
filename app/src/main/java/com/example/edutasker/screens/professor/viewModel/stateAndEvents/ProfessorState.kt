package com.example.edutasker.screens.professor.viewModel.stateAndEvents

import com.example.edutasker.model.NotificationsDetails
import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.model.ProfessorBasicModel
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.model.TaskModel
import com.example.edutasker.model.TasksWithStudentImageModel

data class ProfessorState(
    val events: ProfessorEvents = ProfessorEvents.None,
    val uiEvents: ProfessorUiEvents = ProfessorUiEvents.None,
    val isAddDialogVisible: Boolean = false,
    val professorSubjects: List<String> = listOf(),
    val searchedStudentsForAssignment: List<StudentPreviewAsListModel> = listOf(),
    val selectedSearchedStudents: List<StudentPreviewAsListModel> = listOf(),
    val selectedStudentForAddingAssignment: StudentPreviewAsListModel? = null,
    val studentsToAppearOnCentralRow: List<StudentPreviewAsListModel> = listOf(),
    val messageErrorId: Int = -1,
    val searchedStudents: List<StudentPreviewAsListModel> = listOf(),
    val selectedStudentFromSearch: StudentPreviewAsListModel = StudentPreviewAsListModel(
        "",
        "",
        ""
    ),
    val keyword: String = "",
    val tasks: List<TaskModel> = listOf(),
    val allTasksByEveryone: List<TasksWithStudentImageModel> = listOf(),
    val allTasksByProfessorStudent: List<TasksWithStudentImageModel> = listOf(),
    val openedTask: OpenedTaskModel = OpenedTaskModel(
        TaskModel(), StudentPreviewAsListModel(),
        ProfessorBasicModel()
    ),
    val isTaskOpened: Boolean = false,
    val unreadNotificationsCount: Int = 0,
    val notifications: List<NotificationsDetails> = listOf(),
    val isNotificationDialogVisible: Boolean = false,
    val unreadTaskIds: Set<String> = emptySet()
)