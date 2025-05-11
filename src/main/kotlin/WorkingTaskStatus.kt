import java.time.Instant

data class WorkingTaskStatus(
    override val message: String?,
    override val timestamp: String? = Instant.now().toString()
) : TaskStatus {
    override val state = TaskState.WORKING
    override fun toWorking(task: Task) {
        throw Error("Task ${task.id} already in WORKING state")
    }
    override fun toFailed(task: Task) {
        val message = "[ERROR] (${this.timestamp}) Task id ${task.id} status now is: FAILED"
        task.updateStatus(FailedTaskStatus(message))
    }
    override fun toInputRequired(task: Task) {
        val message = "[ALERT] (${this.timestamp}) Task id ${task.id} status now is: INPUT_REQUIRED"
        task.updateStatus(InputRequiredTaskStatus(message))
    }
    override fun toComplete(task: Task) {
        val message = "[NOTICE] (${this.timestamp}) Task id ${task.id} status now is: COMPLETED"
        task.updateStatus(CompletedTaskStatus(message))
    }
}