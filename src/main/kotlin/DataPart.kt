import kotlinx.serialization.json.JsonElement

data class DataPart(val data: Map<String, JsonElement>) : Part {
    override val type = PartType.DATA
}