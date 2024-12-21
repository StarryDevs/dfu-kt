package starry.dfu.codec

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import kotlin.reflect.KProperty1

fun <O, A> MapCodec<A>.forGetter(field: KProperty1<O, A>): RecordCodecBuilder<O, A> = forGetter { field.get(it) }