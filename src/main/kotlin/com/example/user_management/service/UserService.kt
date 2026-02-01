package com.example.user_management.service

import com.example.user_management.dto.CreateUserRequest
import com.example.user_management.dto.UpdateUserRequest
import com.example.user_management.entity.User
import com.example.user_management.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.NoSuchElementException

@Service
class UserService (
    private val userRepository: UserRepository
) {
    @Transactional
    fun createUser(request: CreateUserRequest): User {
        if(userRepository.existsByEmail(request.email)) {
            throw IllegalArgumentException("Email ${request.email} is already in use.")
        }

        val user = User(
            name = request.name,
            email = request.email
        )

        return userRepository.save(user);
    }

    fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow {
            NoSuchElementException("User with id $id not found.")
        }
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }

    @Transactional
    fun updateUser(id: Long, request: UpdateUserRequest): User {
        val user = getUser(id)
        user.name = request.name
        user.email = request.email
        return user
    }

    @Transactional
    fun deleteUser(id: Long) {
        if(!userRepository.existsById(id)) {
            throw NoSuchElementException("User with id $id not found.")
        }
        userRepository.deleteById(id)
    }
}