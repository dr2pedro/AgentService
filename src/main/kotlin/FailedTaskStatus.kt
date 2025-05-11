import java.time.Instant

class FailedTaskStatus(
    override val message: String?,
    override val timestamp: String? = Instant.now().toString()
) : TaskStatus {
    override val state = TaskState.FAILED
    override fun toWorking(task: Task) {
        val message = "[INFO] (${this.timestamp}) Task id ${task.id} status now is: WORKING"
        task.updateStatus(WorkingTaskStatus(message))
    }
    override fun toFailed(task: Task) {
        throw Error("Task ${task.id} already in FAILED status")
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