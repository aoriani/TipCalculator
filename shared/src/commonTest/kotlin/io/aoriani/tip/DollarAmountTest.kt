package io.aoriani.tip

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class DollarAmountTest {

    @Test
    fun testToStringFormatting() {
        assertEquals("10.00", DollarAmount("10").toString())
        assertEquals("10.50", DollarAmount("10.5").toString())
        assertEquals("10.55", DollarAmount("10.55").toString())
    }

    @Test
    fun testRoundingHalfEven() {
        // Half-even rounding (Banker's rounding)
        // 10.525 rounds to 10.52
        // 10.535 rounds to 10.54
        assertEquals("10.52", DollarAmount("10.525").toString())
        assertEquals("10.54", DollarAmount("10.535").toString())
        
        assertEquals("10.50", DollarAmount("10.504").toString())
        assertEquals("10.51", DollarAmount("10.506").toString())
    }

    @Test
    fun testMultiplication() {
        val amount = DollarAmount("100.00")
        val rate = DollarAmount("0.15")
        val result = amount * rate
        assertEquals("15.00", result.toString())
    }

    @Test
    fun testMultiplicationWithRounding() {
        val amount = DollarAmount("84.50")
        val rate = DollarAmount("0.18")
        val result = amount * rate // 15.21
        assertEquals("15.21", result.toString())

        val amount2 = DollarAmount("84.50")
        val rate2 = DollarAmount("0.20")
        val result2 = amount2 * rate2 // 16.90
        assertEquals("16.90", result2.toString())
    }

    @Test
    fun testEquality() {
        val a = DollarAmount("10.00")
        val b = DollarAmount("10")
        val c = DollarAmount("10.001")
        val d = DollarAmount("10.00")

        assertEquals(a, b)
        assertEquals(a, d)
        assertNotEquals(a, c)
        assertEquals(a.hashCode(), b.hashCode())
    }

    @Test
    fun testZero() {
        assertEquals("0.00", DollarAmount("0").toString())
        assertEquals("0.00", DollarAmount("0.00").toString())
    }
    
    @Test
    fun testNegativeZero() {
        // Apple implementation has a specific check for -0.00
        assertEquals("0.00", DollarAmount("-0.00").toString())
    }
}
