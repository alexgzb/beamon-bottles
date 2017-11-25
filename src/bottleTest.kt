import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

@Test fun `calculate steps given different order of input gives different solutions`() {
    val result1 = calculateSteps(4, 3, 5)
    val result2 = calculateSteps(4, 5, 3)
    assertNotEquals(result1, result2)
}

@Test fun `Given same parameters calculateSteps returns the same result`() {
    val result1 = calculateSteps(4, 3, 5)
    val result2 = calculateSteps(4, 3, 5)
    assertEquals(result1.steps.size, result2.steps.size)
    assertEquals(result1.values.size, result2.values.size)
}

@Test fun `Given impossible values then early return`() {
    val result = calculateSteps(4, 3, 6)
    assertEquals(1, result.steps.size)
    assertEquals("The solution can not be calculated given the bottle sizes and the target", result.steps[0])
}

@Test fun `Given bottle size 1 and target 1 reach goal in 1 step` () {
    val result = calculateSteps(1, 1,2)
    assertEquals(1, result.steps.size)
    assertEquals("1. Fill 1L bottle", result.steps[0])
}

@Test fun `given 3 and 5 gcd returns 1`() {
    assertEquals(1, greatestCommonDivisor(3, 5))
}

@Test fun `given 5 and 3 gcd returns 1`() {
    assertEquals(1, greatestCommonDivisor(5, 3))
}
