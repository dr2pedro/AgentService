import kotlinx.serialization.json.JsonElement

data class FilePart(val file: FileContent, val metadata: Map<String, JsonElement>? = null) : Part {
    override val type = PartType.FILE
}