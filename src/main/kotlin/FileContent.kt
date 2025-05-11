data class FileContent (
    val name: String? = null,
    val mimeType: String? = null,
    val bytes: String? = null,
    val uri: String? = null
) {
    init {
        if(bytes != null || uri != null) {
            throw IllegalArgumentException("Either bytes or uri must be provided")
        }
    }
}