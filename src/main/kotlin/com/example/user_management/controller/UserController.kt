package com.example.user_management.controller

import com.example.user_management.dto.CreateUserRequest
import com.example.user_management.dto.UpdateUserRequest
import com.example.user_management.entity.User
import com.example.user_management.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController (
    private val userService: UserService
) {

    @PostMapping
    fun createUser(@Valid @RequestBody request: CreateUserRequest) : User {
        return userService.createUser(request)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) : User {
        return userService.getUser(id)
    }

    @GetMapping
    fun getAllUsers(): List<User> {
        return userService.getAllUsers()
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @Valid @RequestBody request: UpdateUserRequest): User {
        return userService.updateUser(id, request)
    }
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        return userService.deleteUser(id)
    }
}