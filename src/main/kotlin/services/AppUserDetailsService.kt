package services

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import repositaries.UserRepository

@Component
class AppUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails { //Принимает попытку загрузить UserDetails пользователя, соответствующий имени пользователя, переданному в функцию
        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("A user with the username $username doesn't exist")

        //val authorities = ArrayList<GrantedAuthority>()
        //user.roles?.forEach { role -> authorities.add(SimpleGrantedAuthority(role.name)) }

        return User(user.username, user.password, ArrayList<GrantedAuthority>())
    }
}