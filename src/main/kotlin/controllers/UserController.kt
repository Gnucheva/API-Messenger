package controllers

import components.UserAssembler
import helpers.objects.UserListVO
import helpers.objects.UserVO
import models.User
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import repositaries.UserRepository
import services.UserServiceImpl
import javax.servlet.http.HttpServletRequest

//Этот контроллер сопоставляет относящиеся к пользовательскому ресурсу HTTP запросы с действиями в классе, которые обрабатывают и отвечают на HTTP запрос

@RestController //Определяет что классом является REST - контроллер
@RequestMapping("/users") //Сопоставляет все запросы с путями, начинающимися с user/UserController
class UserController(
    val userService: UserServiceImpl,
    val userAssembler: UserAssembler, val userRepository: UserRepository
) {

    @PostMapping
    @RequestMapping("/registrations")
    //Комбинация этих двух аннотаций сопоставляет все запросы POST с путем /user/registrations
    fun create(@Validated @RequestBody userDetails: User): ResponseEntity<UserVO> { //RequestBody привязвает значения JSON,отправленные в теле запроса POST к userDetails
        //Validated гарантирует , что JSON параметры действительны
        val user = userService.attemptRegistration(userDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping
    @RequestMapping("/{user_id}")
    fun show(@PathVariable("user_id") userId: Long): ResponseEntity<UserVO> {
        val user = userService.retrieveUserData(userId)
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping
    @RequestMapping("/details")
    fun echoDetails(request: HttpServletRequest): ResponseEntity<UserVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        return ResponseEntity.ok(userAssembler.toUserVO(user))
    }

    @GetMapping
    fun index(request: HttpServletRequest): ResponseEntity<UserListVO> {
        val user = userRepository.findByUsername(request.userPrincipal.name) as User
        val users = userService.listUsers(user)

        return ResponseEntity.ok(userAssembler.toUserListVO(users))
    }

    @PutMapping
    fun update(@RequestBody updateDetails: User, request: HttpServletRequest):
            ResponseEntity<UserVO> {
        val currentUser = userRepository.findByUsername(request.userPrincipal.name)
        userService.updateUserStatus(currentUser as User, updateDetails)
        return ResponseEntity.ok(userAssembler.toUserVO(currentUser))
    }
}