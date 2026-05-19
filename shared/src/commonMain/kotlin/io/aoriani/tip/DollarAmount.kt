package io.aoriani.tip

expect class DollarAmount(value: String) {
    operator fun times(other: DollarAmount): DollarAmount
    operator fun plus(other: DollarAmount): DollarAmount
    override fun toString(): String
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}