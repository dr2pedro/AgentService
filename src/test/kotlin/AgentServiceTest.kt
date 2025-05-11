import com.aallam.openai.api.chat.UserMessageBuilder
import kotlin.test.Test
import kotlin.test.assertEquals

class AgentServiceTest {
    @Test
    fun `Ao enviar os parametros deve criar uma Task`() {
        val taskRepository = InMemoryTaskRepository()
        val agentService = AgentService(taskRepository)
        val message = UserMessageBuilder()
        message.content = "Hello, how are you?"
        val params = AgentService.TaskParams("1", "a", message.build())
        val task = agentService.sendParams(params)
        assertEquals("1", task.id)
        assertEquals(TaskState.SUBMITTED, task.status.state)
    }
}