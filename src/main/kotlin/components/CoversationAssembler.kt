package components

import helpers.objects.ConversationListVO
import helpers.objects.ConversationVO
import helpers.objects.MessageVO
import models.Conversation
import org.springframework.stereotype.Component
import services.ConversationServiceImpl

@Component
class ConversationAssembler(val conversationService: ConversationServiceImpl, val messageAssembler: MessageAssembler) {

    fun toConversationVO(conversation: Conversation, userId: Long): ConversationVO {
        val conversationMessages: ArrayList<MessageVO> = ArrayList()
        conversation.messages.mapTo(conversationMessages) { messageAssembler.toMessageVO(it) }
        return ConversationVO(
            conversation.id, conversationService
                .nameSecondParty(conversation, userId), conversationMessages
        )
    }

    fun toConversationListVO(conversations: ArrayList<Conversation>, userId: Long): ConversationListVO {
        val conversationVOList = conversations.map { toConversationVO(it, userId) }
        return ConversationListVO(conversationVOList)
    }
}