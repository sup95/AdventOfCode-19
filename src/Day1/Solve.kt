package Day1

fun main() {
    val input = Utils.readDayInputAsIntList(1)
    solvePart1(input)    //3273471
    solvePart2(input)   //4907345
}

private fun solvePart1(input: List<Int>) {
    val result = input.sumBy { it/3 - 2 }
    println(result)
}

private fun solvePart2(input: List<Int>) {
    val result = input.sumBy { computeTotalFuel(it) }
    println(result)
}

private fun computeTotalFuel(input: Int): Int {
    val requiredFuel = input / 3 - 2
    if (requiredFuel <= 0) return 0
    return requiredFuel + computeTotalFuel(requiredFuel)
}