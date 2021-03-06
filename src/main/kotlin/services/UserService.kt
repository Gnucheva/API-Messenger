package services

import models.User

interface UserService {
    fun attemptRegistration(userDetails: User): User

    fun listUsers(currentUser: User): List<User>

    fun retrieveUserData(username: String): User?

    fun retrieveUserData(id: Long): User?

    fun usernameExists(username: String): Boolean
}
//В интерфейсе определены функции, которые следует объявить классами, реализующими UserService, переопределяющие функции для listUsers итд