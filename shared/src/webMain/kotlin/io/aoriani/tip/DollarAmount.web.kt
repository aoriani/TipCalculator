package io.aoriani.tip

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsModule

@OptIn(ExperimentalWasmJsInterop::class)
@JsModule("big.js")
@Suppress("UNRESOLVED_REFERENCE")
@kotlin.js.JsNonModule
private external class Big {
    constructor(value: String)
    fun plus(other: Big): Big
    fun times(other: Big): Big

    fun toFixed(decimals: Int, roundingMode: Int): String

    companion object {
        val roundHalfEven: Int
    }
}


actual class DollarAmount actual constructor(value: String) {
    actual operator fun times(other: DollarAmount): DollarAmount {
        TODO("Not yet implemented")
    }

    actual operator fun plus(other: DollarAmount): DollarAmount {
        TODO("Not yet implemented")
    }

}