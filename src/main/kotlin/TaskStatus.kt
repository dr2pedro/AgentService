interface TaskStatus {
    val state: TaskState
    val message: String?
    val timestamp: String?

    fun toWorking(task: Task)
    fun toFailed(task: Task)
    fun toInputRequired(task: Task)
    fun toComplete(task: Task)
}
