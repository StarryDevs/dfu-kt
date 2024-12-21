package starry.dfu

import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import org.junit.jupiter.api.Test
import starry.dfu.codec.PairCodec
import starry.dfu.codec.TripleCodec
import kotlin.test.assertEquals

class TupleCodecTest {

    @Test
    fun test() {
        // test triple and pair codec
        val tripleCodec = TripleCodec(
            Codec.INT,
            Codec.STRING,
            Codec.BOOL
        )
        val pairCodec = PairCodec(
            Codec.INT,
            Codec.STRING
        )
        val triple = Triple(1, "2", true)
        val pair = Pair(1, "2")
        val tripleResult = tripleCodec.encodeStart(JsonOps.INSTANCE, triple).result()
        val pairResult = pairCodec.encodeStart(JsonOps.INSTANCE, pair).result()
        assertEquals(triple, tripleCodec.decode(JsonOps.INSTANCE, tripleResult.get()).orThrow.first)
        assertEquals(pair, pairCodec.decode(JsonOps.INSTANCE, pairResult.get()).orThrow.first)
    }

}