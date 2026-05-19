package io.aoriani.tip

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsModule

@OptIn(ExperimentalWasmJsInterop::class)
@JsModule("big.js")
private external class Big {
    constructor(value: String)
    fun plus(other: Big): Big
    fun times(other: Big): Big

    fun toFixed(decimals: Int, roundingMode: Int): String

    companion object {
        val roundHalfEven: Int
    }
}


actual class DollarAmount {

    private val delegate: Big

    /**
     * Private constructor for internal use, allowing creation of [DollarAmount] from a `Big` instance.
     * @param delegate The `Big` instance representing the dollar amount.
     */
    private constructor(delegate: Big) {
        this.delegate = delegate
    }

    actual constructor(value: String) {
        delegate = Big(value)
    }

    actual operator fun times(other: DollarAmount): DollarAmount {
        return DollarAmount(delegate.times(other.delegate))
    }

    actual operator fun plus(other: DollarAmount): DollarAmount {
        return DollarAmount(delegate.plus(other.delegate))
    }


    actual override operator fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        if (other !is DollarAmount) return false
        return this.toString() == other.toString()
    }

    actual override fun toString(): String {
        val returnString = delegate.toFixed(2, Big.roundHalfEven)

        return if (returnString == "-0.00") "0.00" else returnString
    }

    actual override fun hashCode(): Int {
        return toString().hashCode()
    }

}