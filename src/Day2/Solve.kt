package Day2

import Utils.readDayInputAsString

fun main() {
    val input = readDayInputAsString(2).split(",").map { it.toInt() }
    val noun = 12
    val verb = 2

    println(solvePart1(input, noun, verb))  //5434663
    println(solvePart2(input, noun, verb))  //4559
}

private fun solvePart1(programInput: List<Int>, noun: Int, verb: Int) : Int {
    val input = programInput.toMutableList()
    input[1] = noun
    input[2] = verb
    for(i in input.indices step 4) {
        when(input[i]) {
            99 -> return input[0]
            1 -> input[input[i+3]] = input[input[i+1]] + input[input[i+2]]
            2 -> input[input[i+3]] = input[input[i+1]] * input[input[i+2]]
        }
    }
    return input[0]
}

private fun solvePart2(programInput: List<Int>, noun: Int, verb: Int) : Int {
    for(i in 0..99) {
        for(j in 0..99) {
            if(solvePart1(programInput, i, j) == 19690720)
                return 100 * i + j
        }
    }
    return 100 * noun + verb
}