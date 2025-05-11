import kotlinx.serialization.json.JsonElement

data class TextPart(val text: String, val metadata: Map<String, JsonElement>? = null) : Part {
    override val type = PartType.TEXT
}