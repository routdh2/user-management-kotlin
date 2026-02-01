package com.example.user_management.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdateUserRequest (
    @field:NotBlank("Name must not be blank")
    @field:Size(min=1, max=100, message="Name must be between 1 and 100 characters")
    val name: String,

    @field:Email(message = "Invalid email format")
    @field:NotBlank("Email must not be blank")
    val email: String
)