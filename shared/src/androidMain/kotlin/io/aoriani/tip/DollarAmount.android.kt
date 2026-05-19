package io.aoriani.tip

import java.math.BigDecimal
import java.math.RoundingMode

actual class DollarAmount {

    private val delegate: BigDecimal

    private constructor(value: BigDecimal) {
        delegate = value
    }

    actual constructor(value: String) {
        delegate = BigDecimal(value)
    }
    actual operator fun times(other: DollarAmount): DollarAmount {
        return DollarAmount(delegate * other.delegate)
    }
    actual operator fun plus(other: DollarAmount): DollarAmount {
        return DollarAmount(delegate + other.delegate)
    }

    actual override fun toString(): String {
        return delegate.setScale(2, RoundingMode.HALF_EVEN).toString()
    }

    actual override operator fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        if (other !is DollarAmount) return false
        return this.toString() == other.toString()
    }

    actual override fun hashCode(): Int {
        return toString().hashCode()
    }
}