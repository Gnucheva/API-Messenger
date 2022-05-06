package listeners

import models.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

class UserListener {
    @PrePersist //вызывать до сохранения
    @PreUpdate // До обновления
    fun hashPassword(user: User) {
        user.password = BCryptPasswordEncoder().encode(user.password)
    }

}
//Функция hashPassword выполняет процедуру хеширования,заменяя строковое значение в свойстве password на хэшированный эквивалент с помощью BCrypt
