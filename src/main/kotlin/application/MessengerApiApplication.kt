package application

import models.User
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import repositaries.UserRepository

@SpringBootApplication
class MessengerApiApplication
/*{
    @Bean
    fun init(userRepository: UserRepository) = CommandLineRunner {
        userRepository.save(User("Nick", "08184209188", "Calamari911"))
    }
}*/

fun main(args: Array<String>) {
    SpringApplication.run(MessengerApiApplication::class.java, *args)
}
