class InMemoryTaskRepository : TaskRepository {
    val tasks = mutableMapOf<String, Task>()

    override suspend fun findById(id: String): Task? {
        return tasks[id]
    }

    override suspend fun save(task: Task): Task {
        tasks.put(task.id, task)
        return task
    }

    override suspend fun update(previousTask: Task, updatedTask: Task): Task {
        tasks[previousTask.id] = updatedTask
        return updatedTask
    }
}