package io.aoriani.tip

import kotlin.test.Test
import kotlin.test.assertEquals

class DollarAmountTest {
    @Test
    fun j () {
        assertEquals("100.00", DollarAmount("100").toString())
    }
}
