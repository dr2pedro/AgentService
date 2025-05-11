import java.time.Instant

data class InputRequiredTaskStatus(
    override val message: String?,
    override val timestamp: String? = Instant.now().toString()
) : TaskStatus {
    override val status = TaskState.INPUT_REQUIRED
    override fun toWorking(task: Task) {
        val message = "[INFO] (${this.timestamp}) Task id ${task.id} status now is: WORKING"
        task.updateStatus(WorkingTaskStatus(message))
    }
    override fun toFailed(task: Task) {
        val message = "[ERROR] (${this.timestamp}) Task id ${task.id} status now is: FAILED"
        task.updateStatus(FailedTaskStatus(message))
    }
    override fun toInputRequired(task: Task) {
        throw Error("Task ${task.id} already in INPUT_REQUIRED state")
    }
    override fun toComplete(task: Task) {
        val message = "[NOTICE] (${this.timestamp}) Task id ${task.id} status now is: COMPLETED"
        task.updateStatus(CompletedTaskStatus(message))
    }
}