data class Message (
    val role: MessageRole,
    val parts: List<Part>,
    val metadata: Map<String, Any>? = null
)