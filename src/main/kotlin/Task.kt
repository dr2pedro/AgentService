import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import java.time.Instant

data class Task(
    val id: String,
    val sessionId: String? = null,
    val history: List<Message>? = null,
    val metadata: Map<String, JsonPrimitive>? = null
) {
    var status: TaskStatus = SubmittedTaskStatus("[NOTICE] (${Instant.now()}) Task id ${this.id} status now is: SUBMITTED")
        private set
    var artifacts: List<Artifact>? = null
        private set
    fun updateStatus(taskStatus: TaskStatus) {
        this.status = taskStatus
    }
    fun toWorkingStatus() = this.status.toWorking(this)
    fun toFailedStatus() = this.status.toFailed(this)
    fun toInputRequiredStatus() = this.status.toInputRequired(this)
    fun toCompleteStatus() = this.status.toComplete(this)
    fun addArtifact(artifact: Artifact) {
        this.artifacts = (this.artifacts ?: emptyList()) + artifact
    }
}
