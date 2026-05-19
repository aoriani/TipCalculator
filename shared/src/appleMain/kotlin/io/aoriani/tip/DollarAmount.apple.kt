package io.aoriani.tip

import platform.Foundation.NSDecimalNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import platform.Foundation.NSNumberFormatterRoundHalfEven

actual class DollarAmount  {

    private val delegate: NSDecimalNumber

    private constructor(delegate: NSDecimalNumber) {
        this.delegate = delegate
    }
    actual constructor(value: String) {
        this.delegate = NSDecimalNumber(value)
    }
    actual operator fun times(other: DollarAmount): DollarAmount {
        return DollarAmount(delegate.decimalNumberByMultiplyingBy(other.delegate))
    }
    actual operator fun plus(other: DollarAmount): DollarAmount {
        return DollarAmount(delegate.decimalNumberByAdding(other.delegate))
    }

    actual override fun toString(): String {
        val numberFormatter = NSNumberFormatter().apply {
            numberStyle = NSNumberFormatterDecimalStyle
            usesGroupingSeparator = false
            minimumFractionDigits = 2.toULong()
            maximumFractionDigits = 2.toULong()
            roundingMode = NSNumberFormatterRoundHalfEven
        }

        val returnString = numberFormatter.stringFromNumber(delegate) ?: delegate.toString()

        return if (returnString == "-0.00") "0.00" else returnString
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