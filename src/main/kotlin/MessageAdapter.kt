import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.core.Role

// TODO: Cansei. Mas quem tem que chamar esse Adaptador é o AgentService
class MessageAdapter {
    fun openAIChatMessageToMessage(openAIChatMessage: ChatMessage): Message? {
        var message: Message? = null
        when (openAIChatMessage.role) {
            Role.User -> {
                val textPart = TextPart(openAIChatMessage.content ?: "")
                message = Message(MessageRole.USER, listOf(textPart))
            }

            Role.System, Role.Assistant, Role.Function, Role.Tool -> {
                val textPart = TextPart(openAIChatMessage.content ?: "")
                message = Message(MessageRole.AGENT, listOf(textPart))
            }
        }
        return message
    }
}
