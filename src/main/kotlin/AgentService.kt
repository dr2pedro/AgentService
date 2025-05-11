import com.aallam.openai.api.chat.ChatMessage
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.JsonPrimitive

class AgentService(
    private val taskRepository: TaskRepository
) {
    private fun processTask(task: Task): Task {
        return task
    }
    fun sendParams(params: TaskParams) = runBlocking {
        try {
            val persistedTask = taskRepository.findById(params.id)
            when(persistedTask) {
                null -> {
                    val task = Task(params.id, params.sessionId, metadata = params.metadata)
                    val processedTask = processTask(task)
                    return@runBlocking taskRepository.save(processedTask)
                }
                else -> {
                    val processedTask = processTask(persistedTask)
                    return@runBlocking taskRepository.update(persistedTask, processedTask)
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }
    fun getTask(params: TaskParams) = runBlocking {
        return@runBlocking taskRepository.findById(params.id)
    }
    class TaskParams(
        val id: String,
        val sessionId: String? = null,
        message: ChatMessage,
        val historyLength: Int? = null,
        val metadata: Map<String, JsonPrimitive>? = null
    ) {
        private val messageAdapter = MessageAdapter()
        val message: Message? = this.messageAdapter.openAIChatMessageToMessage(message)
    }
}