package com.example.edutasker.model

data class StudentModel(
    val stId:String,
    val username:String,
    val name:String,
    val image:String,
    val subject:List<String>
):User

data class ProfessorModel(
    val profId:String,
    val username:String,
    val name:String,
    val image:String,
    val subject:List<String>
):User


interface User