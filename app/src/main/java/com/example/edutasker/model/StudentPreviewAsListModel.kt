package com.example.edutasker.model

data class StudentPreviewAsListModel(
    val studentId: String = "",
    val username: String = "",
    val image: String = "",
)

data class StudentBasicInfoForPreviewIntoList(
    val studentId: String,
    val username: String,
    val image: String,
    val subjects: List<String>,
)

