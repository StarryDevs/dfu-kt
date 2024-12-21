package starry.dfu.codec

import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.DynamicOps
import com.mojang.serialization.codecs.RecordCodecBuilder


class TripleCodec<A, B, C>(private val codecFirst: Codec<A>, private val codecSecond: Codec<B>, private val codecThird: Codec<C>) :
    Codec<Triple<A, B, C>> {

    private val record = RecordCodecBuilder.create {
        it.group(
            codecFirst.fieldOf("1").forGetter(Triple<A, B, C>::first),
            codecSecond.fieldOf("2").forGetter(Triple<A, B, C>::second),
            codecThird.fieldOf("3").forGetter(Triple<A, B, C>::third)
        ).apply(it) { a, b, c -> Triple(a, b, c) }
    }

    override fun <T> encode(value: Triple<A, B, C>, ops: DynamicOps<T>, rest: T): DataResult<T> {
        return record.encode(value, ops, rest)
    }

    override fun <T> decode(ops: DynamicOps<T>, input: T): DataResult<Pair<Triple<A, B, C>, T>> {
        return record.decode(ops, input)
    }

}

class PairCodec<A, B>(private val codecFirst: Codec<A>, private val codecSecond: Codec<B>) :
    Codec<kotlin.Pair<A, B>> {

    private val record = RecordCodecBuilder.create {
        it.group(
            codecFirst.fieldOf("1").forGetter(kotlin.Pair<A, B>::first),
            codecSecond.fieldOf("2").forGetter(kotlin.Pair<A, B>::second),
        ).apply(it) { a, b -> kotlin.Pair(a, b) }
    }

    override fun <T> encode(value: kotlin.Pair<A, B>, ops: DynamicOps<T>, rest: T): DataResult<T> {
        return record.encode(value, ops, rest)
    }

    override fun <T> decode(ops: DynamicOps<T>, input: T): DataResult<Pair<kotlin.Pair<A, B>, T>> {
        return record.decode(ops, input)
    }

}
