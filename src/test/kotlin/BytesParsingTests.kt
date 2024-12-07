import com.johnysoft.bytes_parse.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BytesParsingTests {

    @Test
    fun `should convert signed dword little endian correctly`() {
        assertEquals(0x00000010, byteArrayOf(0x10,0,0,0).signedDwordLe(0))
    }

    @Test
    fun `should convert signed dword big endian correctly`() {
        assertEquals(0x10000000, byteArrayOf(0x10,0,0,0).signedDwordBe(0))
    }

    @Test
    fun `should convert signed word little endian correctly`() {
        assertEquals(0x0020, byteArrayOf(0x20,0).signedWordLe(0))
    }

    @Test
    fun `should convert signed word big endian correctly`() {
        assertEquals(0x2000, byteArrayOf(0x20,0).signedWordBe(0))
    }

    @Test
    fun `should convert unsigned dword little endian correctly`() {
        assertEquals(0x000000ff.toUInt(), byteArrayOf(0xff.toByte(),0,0,0).unsignedDwordLe(0))
    }

    @Test
    fun `should convert unsigned dword big endian correctly`() {
        assertEquals(0xff000000.toUInt(), byteArrayOf(0xff.toByte(),0,0,0).unsignedDwordBe(0))
    }

    @Test
    fun `should convert unsigned word little endian correctly`() {
        assertEquals(0x00ff.toUShort(), byteArrayOf(0xff.toByte(),0).unsignedWordLe(0))
    }

    @Test
    fun `should convert unsigned word big endian correctly`() {
        assertEquals(0xff00.toUShort(), byteArrayOf(0xff.toByte(),0).unsignedWordBe(0))
    }
}
