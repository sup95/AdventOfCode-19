package Utils

import java.io.File

fun readDayInputAsString(day: Int) : String {
    val fileName = "${System.getProperty("user.dir")}/src/Day${day}/input.txt"
    return File(fileName).bufferedReader().readText()
}

fun readDayInputAsList(day: Int) : List<String> {
    val fileName = "${System.getProperty("user.dir")}/src/Day${day}/input.txt"
    return File(fileName).bufferedReader().readLines()
}

fun readDayInputAsIntList(day: Int) : List<Int> = readDayInputAsList(day).map { it.toInt() }