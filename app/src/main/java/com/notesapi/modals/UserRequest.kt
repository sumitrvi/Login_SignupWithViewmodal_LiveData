package com.notesapi.modals

data class UserRequest(
    val email: String,
    val password: String,
    val username: String
)