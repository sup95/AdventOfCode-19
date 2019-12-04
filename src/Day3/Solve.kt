package Day3

import kotlin.math.abs

fun main() {
    val (wire1, wire2) = Utils.readDayInputAsString(3).split("\n")

    val wire1PathPoints = getWirePathPoints(wire1, 0, 0)
    val wire2PathPoints = getWirePathPoints(wire2, 0, 0)

    val intersectingPoints = wire1PathPoints.map { it.intersectionPoint }.toSet().intersect(wire2PathPoints.map { it.intersectionPoint }).toMutableSet()
    intersectingPoints.remove(Pair(0, 0))
    val part1Result = getMinimumManhattanDistance(intersectingPoints, 0, 0)

    val part2a = wire1PathPoints.filter { it.intersectionPoint in intersectingPoints }
    val part2b = wire2PathPoints.filter { it.intersectionPoint in intersectingPoints }

    var min = 1000000
    part2a.union(part2b).groupBy { it.intersectionPoint }.forEach { (key, value) ->
        val total = value[0].stepCountAtIntersectionPoint + value[1].stepCountAtIntersectionPoint
        if(total < min) min = total
    }

    val part2Result = getMinimumStepsForIntersection(intersectingPoints, 0, 0)

    println(part1Result)     //529
    println(min)
}

data class Result(val intersectionPoint: Pair<Int, Int>, val stepCountAtIntersectionPoint: Int)

fun conditionalSet(wireDirectionsAndSteps: Set<Result>, newResult: Result) : Boolean {
    return newResult.intersectionPoint in wireDirectionsAndSteps.map { it.intersectionPoint }
}

fun getWirePathPoints(wire: String, originX: Int, originY: Int) : Set<Result> {
    val wireDirectionsAndSteps = wire.split(",").map { Pair(it[0], it.substring(1).toInt()) }

    var (x, y) = Pair(originX, originY)

    val allWirePointsWithStepCounts = mutableSetOf<Result>()
    var stepCount = 0

    wireDirectionsAndSteps.forEach {
        when (it.first) {
            'L' -> {
                val endpoint = x - it.second
                for(i in endpoint..x) {
                    val pointsPair = Pair(i, y)
                    if (conditionalSet(allWirePointsWithStepCounts, Result(pointsPair, stepCount++)))
                        allWirePointsWithStepCounts.add(Result(pointsPair, stepCount++))
                }
                x = endpoint
            }
            'R' -> {
                val endpoint = x + it.second
                for(i in x..endpoint) {
                    val pointsPair = Pair(i, y)
                    if (conditionalSet(allWirePointsWithStepCounts, Result(pointsPair, stepCount++)))
                        allWirePointsWithStepCounts.add(Result(pointsPair, stepCount++))
                }
                x = endpoint
            }
            'U' -> {
                val endpoint = y + it.second
                for(i in y..endpoint) {
                    val pointsPair = Pair(x, i)
                    if (conditionalSet(allWirePointsWithStepCounts, Result(pointsPair, stepCount++)))
                        allWirePointsWithStepCounts.add(Result(pointsPair, stepCount++))
                }
                y = endpoint
            }
            'D' -> {
                val endpoint = y - it.second
                for(i in endpoint..y) {
                    val pointsPair = Pair(x, i)
                    if (conditionalSet(allWirePointsWithStepCounts, Result(pointsPair, stepCount++)))
                        allWirePointsWithStepCounts.add(Result(pointsPair, stepCount++))
                }
                y = endpoint
            }
        }
    }

    return allWirePointsWithStepCounts
}

fun getMinimumManhattanDistance(intersectingPoints: Set<Pair<Int, Int>>, originX: Int, originY: Int) : Int {
    return intersectingPoints.map { abs(it.first - originX) + abs(it.second - originY) }.min() ?: 0
}

fun getMinimumStepsForIntersection(intersectingPoints: Set<Pair<Int, Int>>, originX: Int, originY: Int) : Int {
    return 0
}