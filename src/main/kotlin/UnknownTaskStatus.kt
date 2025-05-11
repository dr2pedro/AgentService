import java.time.Instant

class UnknownTaskStatus(
    override val message: String?,
    override val timestamp: String? = Instant.now().toString()
) : TaskStatus {
    override val state = TaskState.UNKNOWN
    override fun toWorking(task: Task) {
        val message = "[INFO] (${this.timestamp}) Task id ${task.id} status now is: WORKING"
        task.updateStatus(WorkingTaskStatus(message))
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
        throw Error("The Task ${task.id} with UNKNOWN state can't be completed")
    }
}