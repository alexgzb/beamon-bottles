import kotlin.collections.ArrayList

val emptyBottle = "Empty %dL bottle"
val pourFromBottleAtoBottleB = "Pour from %dL bottle to %dL bottle"
val fillBottle = "Fill %dL bottle"

class Solution(val steps: ArrayList<String>, val values: MutableList<Pair<Int, Int>>)


fun main(args: Array<String>) {
    calculateBestSolutionAndPrint(4, 3, 5)
    calculateBestSolutionAndPrint(1, 3, 5)
}

fun calculateBestSolutionAndPrint(goal: Int, bottleASize: Int, bottleBSize: Int) {

    val result1 = calculateSteps(goal, bottleASize, bottleBSize)
    val result2 = calculateSteps(goal, bottleBSize, bottleASize)
    val bestSolution = if (result1.steps.size < result2.steps.size) result1 else result2
    println("\nBottle A size: ${bottleASize}L, Bottle B size: ${bottleBSize}L, target: ${goal}L")
    println("Least number of steps required: ${bestSolution.steps.size}")
    println("Steps taken: ")
    for ((index, step) in bestSolution.steps.withIndex()) {
        println("$step  :${bestSolution.values[index]}")
    }
}

fun calculateSteps(goal: Int, bottleASize: Int, bottleBSize: Int): Solution {
    //Initialize values needed
    val steps: ArrayList<String> = arrayListOf()
    val values = mutableListOf<Pair<Int, Int>>()

    //If no solution exists then early return
    val gcd = greatestCommonDivisor(bottleASize, bottleBSize)
    if (goal % gcd != 0) {
        steps.add("The target can not be reached given the bottle sizes")
        values.add(Pair(0, 0))
        return Solution(steps, values)
    }

    //Start by filling bottle A
    var bottleA = bottleASize
    var bottleB = 0
    values.add(Pair(bottleA, bottleB))
    var step = fillBottle.format(bottleASize)
    steps.add("${(steps.size + 1)}. $step")

    while (bottleA != goal && bottleB != goal) {
        //Pour from bottleA to bottleB
        val pouringAmount = minOf(bottleA, (bottleBSize - bottleB))
        bottleA -= pouringAmount
        bottleB += pouringAmount
        values.add(Pair(bottleA, bottleB))
        step = pourFromBottleAtoBottleB.format(bottleASize, bottleBSize)
        steps.add("${(steps.size + 1)}. $step")

        // Reached target value or ridiculously large amount of steps
        if (bottleA == goal || bottleB == goal || steps.size > 100000) {
            break
        }

        //If bottleA is empty then fill it to the max
        if (bottleA == 0) {
            bottleA = bottleASize
            values.add(Pair(bottleA, bottleB))
            step = (fillBottle.format(bottleASize))
            steps.add("${(steps.size + 1)}. $step")
        }

        //If bottleB is full then empty it.
        if (bottleB == bottleBSize) {
            bottleB = 0
            values.add(Pair(bottleA, bottleB))
            step = (emptyBottle.format(bottleBSize))
            steps.add("${(steps.size + 1)}. $step")
        }
    }

    return Solution(steps, values)
}

fun greatestCommonDivisor(a: Int, b: Int): Int {
    return if (b == 0) a else (greatestCommonDivisor(b, a % b))
}