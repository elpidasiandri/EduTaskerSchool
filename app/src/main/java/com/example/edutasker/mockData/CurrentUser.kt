package com.example.edutasker.mockData

import com.example.edutasker.utils.orEmptyIfNull

object CurrentUser {
    var userId: String? = null
    var isStudent: Boolean? = null
    var name: String? = null
    var email: String? = null
    var image: String? = null
    var username: String? = null

    fun setCurrentUserNull() {
        userId = null
        isStudent = null
        name = null
        email = null
        username = null
        image = null
    }

    fun setCurrentUser(
        userId: String,
        isStudent: Boolean,
        name: String,
        email: String,
        image: String,
        username: String,
    ) {
        this.userId = userId
        this.isStudent = isStudent
        this.name = name
        this.email = email
        this.username = username
        this.image = image
    }

    fun getCurrentUserId(): String = userId.orEmptyIfNull()
    fun getCurrentUserImage(): String = image.orEmptyIfNull()
    fun getCurrentUserIfIsStudent(): Boolean = isStudent.orEmptyIfNull()
}