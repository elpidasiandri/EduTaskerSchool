package com.example.edutasker.model

data class TaskModel(
    val taskId:String,
    val subjectName:String,
    val description:String,
    val assignTo:List<StudentModel>,
    val assignBy:ProfessorModel
)