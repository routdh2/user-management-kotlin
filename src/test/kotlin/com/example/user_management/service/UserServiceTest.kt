package com.example.user_management.service

import com.example.user_management.dto.CreateUserRequest
import com.example.user_management.dto.UpdateUserRequest
import com.example.user_management.entity.User
import com.example.user_management.repository.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class UserServiceTest {
    private val userRepository: UserRepository = Mockito.mock(UserRepository::class.java)
    private val userService = UserService(userRepository)

    @Test
    fun createUser() {
        val request = CreateUserRequest(name = "Dhananjay", email = "dhananjay@example.com")

        Mockito.`when`(userRepository.existsByEmail(request.email)).thenReturn(false)
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenAnswer { invocation ->
            val arg = invocation.getArgument<User>(0)
            arg
        }

        val created = userService.createUser(request)

        assertEquals("Dhananjay", created.name)
        assertEquals("dhananjay@example.com", created.email)
        Mockito.verify(userRepository).existsByEmail(request.email)
        Mockito.verify(userRepository).save(Mockito.any(User::class.java))
    }

    @Test
    fun getUser() {
        val user = User(name = "Dhananjay", email = "dhananjay@example.com")
        val id = 1L
        Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))

        val found = userService.getUser(id)

        assertEquals("Dhananjay", found.name)
        assertEquals("dhananjay@example.com", found.email)
        Mockito.verify(userRepository).findById(id)
    }

    @Test
    fun getUser_notFound() {
        val id = 99L
        Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.empty())

        val ex = assertThrows(NoSuchElementException::class.java) {
            userService.getUser(id)
        }
        assertTrue(ex.message!!.contains("User with id $id not found."))
        Mockito.verify(userRepository).findById(id)
    }

    @Test
    fun getAllUsers() {
        val users = listOf(
            User(name = "Dhananjay", email = "dhananjay@example.com"),
            User(name = "Tanmoy", email = "tanmoy@example.com")
        )
        Mockito.`when`(userRepository.findAll()).thenReturn(users)

        val all = userService.getAllUsers()

        assertEquals(2, all.size)
        assertEquals("Dhananjay", all[0].name)
        assertEquals("tanmoy@example.com", all[1].email)
        Mockito.verify(userRepository).findAll()
    }

    @Test
    fun updateUser() {
        val original = User(name = "Dhananjay", email = "dhananjay@example.com")
        val id = 2L
        Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(original))

        val request = UpdateUserRequest(name = "Tanmoy", email = "tanmoy@example.com")
        val updated = userService.updateUser(id, request)

        assertEquals("Tanmoy", updated.name)
        assertEquals("tanmoy@example.com", updated.email)
        Mockito.verify(userRepository).findById(id)
    }

    @Test
    fun deleteUser() {
        val id = 3L
        Mockito.`when`(userRepository.existsById(id)).thenReturn(true)

        userService.deleteUser(id)

        Mockito.verify(userRepository).existsById(id)
        Mockito.verify(userRepository).deleteById(id)
    }

    @Test
    fun deleteUser_notFound() {
        val id = 42L
        Mockito.`when`(userRepository.existsById(id)).thenReturn(false)

        val ex = assertThrows(NoSuchElementException::class.java) {
            userService.deleteUser(id)
        }
        assertTrue(ex.message!!.contains("User with id $id not found."))
        Mockito.verify(userRepository).existsById(id)
    }

}