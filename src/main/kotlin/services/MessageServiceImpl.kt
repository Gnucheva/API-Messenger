package services

import exceptions.MessageEmptyException
import exceptions.MessageRecipientInvalidException
import models.Conversation
import models.Message
import models.User
import org.springframework.stereotype.Service
import repositaries.ConversationRepository
import repositaries.MessageRepository
import repositaries.UserRepository

@Service
class MessageServiceImpl(
    val repository: MessageRepository, val conversationRepository: ConversationRepository,
    val conversationService: ConversationService, val userRepository: UserRepository
) : MessageService {

    @Throws(MessageEmptyException::class, MessageRecipientInvalidException::class)
    override fun sendMessage(sender: User, recipientId: Long, messageText: String): Message {
        val optional = userRepository.findById(recipientId)

        if (optional.isPresent) {
            val recipient = optional.get()

            if (!messageText.isEmpty()) {
                val conversation: Conversation = if (conversationService.conversationExists(sender, recipient)) {
                    conversationService.getConversation(sender, recipient) as Conversation
                } else {
                    conversationService.createConversation(sender, recipient)
                }
                conversationRepository.save(conversation)

                val message = Message(sender, recipient, messageText, conversation)
                repository.save(message)
                return message
            }
        } else {
            throw MessageRecipientInvalidException("The recipient id '$recipientId' is invalid.")
        }
        throw MessageEmptyException()
    }
}