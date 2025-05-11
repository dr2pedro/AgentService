import kotlin.test.Test
import kotlin.test.assertEquals

class TaskTest {
    @Test
    fun `Deve ser capaz de alterar o status de uma Task`() {
        var task = Task("1")
        assertEquals(TaskState.SUBMITTED, task.status.status)
        task.toWorkingStatus()
        assertEquals(TaskState.WORKING, task.status.status)
        task = Task("2")
        task.toFailedStatus()
        assertEquals(TaskState.FAILED, task.status.status)
        task.toInputRequiredStatus()
        assertEquals(TaskState.INPUT_REQUIRED, task.status.status)
        task.toCompleteStatus()
        assertEquals(TaskState.COMPLETED, task.status.status)
    }
    @Test
    fun `Deve ser capaz de adicionar um artefato`() {
        val task = Task("1")
        val textPart = TextPart("Hi")
        val artifact = Artifact(listOf(textPart))
        task.addArtifact(artifact)
        assertEquals(artifact, task.artifacts?.first())
    }
}