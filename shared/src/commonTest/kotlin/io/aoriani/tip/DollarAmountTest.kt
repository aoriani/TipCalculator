package io.aoriani.tip

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class DollarAmountTest {

    @Test
    fun testToStringFormatting() {
        assertEquals("100.00", DollarAmount("100").toString())
        assertEquals("100.50", DollarAmount("100.5").toString())
        assertEquals("100.55", DollarAmount("100.55").toString())
        assertEquals("0.00", DollarAmount("0").toString())
    }

    @Test
    fun testRoundingHalfEven() {
        // Half-even rounding (Banker's rounding) to 2 decimal places
        assertEquals("100.55", DollarAmount("100.554").toString())
        assertEquals("100.56", DollarAmount("100.555").toString()) // Round to even (6)
        assertEquals("100.56", DollarAmount("100.565").toString()) // Round to even (6)
        assertEquals("100.56", DollarAmount("100.556").toString())
        
        assertEquals("1.22", DollarAmount("1.225").toString()) // 2 is even
        assertEquals("1.24", DollarAmount("1.235").toString()) // 3 is odd, round up to 4
    }

    @Test
    fun testAddition() {
        val a = DollarAmount("10.50")
        val b = DollarAmount("20.25")
        assertEquals("30.75", (a + b).toString())
        
        assertEquals("0.00", (DollarAmount("10.50") + DollarAmount("-10.50")).toString())
    }

    @Test
    fun testMultiplication() {
        val a = DollarAmount("10.00")
        val b = DollarAmount("2.5")
        assertEquals("25.00", (a * b).toString())
        
        val c = DollarAmount("3.33")
        val d = DollarAmount("3")
        assertEquals("9.99", (c * d).toString())
    }

    @Test
    fun testEquality() {
        val a = DollarAmount("10.00")
        val b = DollarAmount("10")
        val c = DollarAmount("10.01")
        
        assertEquals(a, b)
        assertEquals(a.hashCode(), b.hashCode())
        assertNotEquals(a, c)
        assertNotEquals(a, Any())
        assertNotEquals(a as Any?, null)
    }

    @Test
    fun testNegativeZero() {
        assertEquals("0.00", DollarAmount("-0.00").toString())
        assertEquals("0.00", DollarAmount("-0").toString())
    }
}
