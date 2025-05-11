import java.time.Instant

class CompletedTaskStatus(
    override val message: String?,
    override val timestamp: String? = Instant.now().toString()
) : TaskStatus {
    override val status = TaskState.COMPLETED
    override fun toWorking(task: Task) {
        throw Error("Task ${task.id} already COMPLETED")
    }
    override fun toFailed(task: Task) {
        throw Error("Task ${task.id} already COMPLETED")
    }
    override fun toInputRequired(task: Task) {
        throw Error("Task ${task.id} already COMPLETED")
    }
    override fun toComplete(task: Task) {
        throw Error("Task ${task.id} already in COMPLETED status")
    }
}