data class Artifact(
    val parts: List<Part>,
    val name: String? = null,
    val description: String? = null,
    val index: Int? = null,
    val append: Boolean? = null,
    val lastChunk: Boolean? = null,
    val metadata: Map<String, Any>? = null
)