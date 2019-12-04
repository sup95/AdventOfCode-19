package Day4

fun main() {
    var part1Result = 0
    var part2Result = 0

    for(i in 357253..892942) {
        val numAsCharArray = i.toString().map { it }
        if (isDigitsNonDecreasing(numAsCharArray)) {
            if(hasAtleastDouble(numAsCharArray)) part1Result++
            if(hasExactlyDouble(numAsCharArray)) part2Result++
        }
    }

    println(part1Result)    //530
    println(part2Result)    //324
}

fun hasAtleastDouble(numAsChar: List<Char>) : Boolean {
    return numAsChar.groupBy { it }.filter { it.value.size >= 2 }.isNotEmpty()
}

fun hasExactlyDouble(numAsChar: List<Char>) : Boolean {
    return numAsChar.groupBy { it }.filter { it.value.size == 2 }.isNotEmpty()
}

fun isDigitsNonDecreasing(numAsChar: List<Char>) : Boolean {
    return numAsChar == numAsChar.sorted()
}