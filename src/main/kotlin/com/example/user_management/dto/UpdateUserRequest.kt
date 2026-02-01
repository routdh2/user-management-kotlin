package com.example.user_management.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UpdateUserRequest (
    @field:NotBlank
    val name: String,

    @field:Email
    val email: String
)