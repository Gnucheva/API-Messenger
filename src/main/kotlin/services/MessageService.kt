package services

import models.Message
import models.User

interface MessageService {

    fun sendMessage(sender: User, recipientId: Long, messageText: String): Message
}