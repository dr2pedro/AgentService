import java.time.Instant

class CompleteTask(
    val task: Task,
    override val message: String?,
    override val timestamp: String? = Instant.now().toString()
) : TaskStatus {
    override val status = TaskState.COMPLETED
}