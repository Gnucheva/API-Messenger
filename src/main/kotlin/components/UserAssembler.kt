package components

import helpers.objects.UserListVO
import helpers.objects.UserVO
import models.User
import org.springframework.stereotype.Component

//Компонент, который собирает (ассемблирует) необходимое значение объекта
@Component
class UserAssembler {

    fun toUserVO(user: User): UserVO {
        return UserVO(
            user.id, user.username, user.phoneNumber,
            user.status, user.createdAt.toString()
        )
    }

    fun toUserListVO(users: List<User>): UserListVO {
        val userVOList = users.map { toUserVO(it) }

        return UserListVO(userVOList)
    }
}