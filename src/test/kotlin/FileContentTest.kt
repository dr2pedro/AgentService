import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class FileContentTest {
    @Test
    fun `Nao deve poder setar os bytes e uri ao mesmo tempo`() {
        assertThrows<IllegalArgumentException> {
            FileContent(bytes = "123", uri = "abc")
        }
    }
}