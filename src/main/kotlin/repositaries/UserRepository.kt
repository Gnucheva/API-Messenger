package repositaries

import models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

    fun findByPhoneNumber(phoneNumber: String): User?
}
//UserRepository расширяет интерфейс CrudRepository.