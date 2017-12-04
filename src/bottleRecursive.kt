fun calculateStepsRecursive(goal: Int, bottleASize: Int, bottleBSize: Int): List<Pair<Int, Int>> {
    //If no solution exists then early return
    val steps = listOf(Pair(0, 0))
    val gcd = greatestCommonDivisor(bottleASize, bottleBSize)
    if (goal % gcd != 0) {
        return steps
    }

    tailrec fun go(bottleA: Int, bottleB: Int, steps: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val newBottleA: Int
        val newBottleB: Int
        val newSteps: List<Pair<Int, Int>>

        // Reached target value or ridiculously large amount of steps
        if (bottleA == goal || bottleB == goal || steps.size > 100000) {
            return steps
        } else {
            when {
                //If bottleA is empty then fill it to the max
                bottleA == 0 -> {
                    newSteps = steps.plus(Pair(bottleASize, bottleB))
                    newBottleA = bottleASize
                    newBottleB = bottleB
                }
                //If bottleB is full empty it
                bottleB == bottleBSize -> {
                    newSteps = steps.plus(Pair(bottleA, 0))
                    newBottleA = bottleA
                    newBottleB = 0
                }
                //Else Pour as much as possible from bottleA to bottleB
                else -> {
                    val pouringAmount = minOf(bottleA, (bottleBSize - bottleB))
                    newBottleA = bottleA - pouringAmount
                    newBottleB = bottleB + pouringAmount
                    newSteps = steps.plus(Pair(newBottleA, newBottleB))
                }
            }
        }
        return go(newBottleA, newBottleB, newSteps)
    }

    //Start by filling bottle A
    val bottleA = bottleASize
    val bottleB = 0
    val newSteps = steps.plus(Pair(bottleA, bottleB))

    //Call the recursive function
    return go(bottleA, bottleB, newSteps)
}