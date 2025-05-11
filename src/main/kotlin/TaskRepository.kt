interface TaskRepository {
    suspend fun findById(id: String): Task?
    suspend fun save(task: Task): Task
    suspend fun update(previousTask: Task, updatedTask: Task): Task
}