package com.example.edutasker.screens.professor.viewModel.stateAndEvents

import com.example.edutasker.model.StudentPreviewAsListModel

data class ProfessorState(
    val events: ProfessorEvents = ProfessorEvents.None,
    val uiEvents: ProfessorUiEvents = ProfessorUiEvents.None,
    val isAddDialogVisible: Boolean = false,
    val professorSubjects: List<String> = listOf(),
    val searchedStudentsForAssignment: List<StudentPreviewAsListModel> = listOf(),
    val selectedSearchedStudents: List<StudentPreviewAsListModel> = listOf(),
    val studentsToAppearOnCentralRow: List<StudentPreviewAsListModel> = listOf(),
    val messageErrorId: Int = -1,
    val searchedStudents: List<StudentPreviewAsListModel> = listOf(),
    val selectedStudentIdFromSearch: String = "",
    val keyword :String = ""
)