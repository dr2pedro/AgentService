interface Message {
    val role: String
    val parts: List<Part>
    val metadata: Map<String, Any>?
}