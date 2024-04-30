package org.example

val boardRules = listOf(
    listOf(
        listOf(3, 1, 6, 24),
        listOf(0, 3, 6, 24),
        listOf(5, 0, 6, 32),
        listOf(2, 2, 6, 36),
        listOf(4, 1, 6, 48),
    ),
    listOf(
        listOf(1, 3, 7, 54),
        listOf(6, 0, 7, 64),
        listOf(3, 2, 7, 72),
        listOf(0, 4, 7, 81),
        listOf(5, 1, 7, 96),
    ),
    listOf(
        listOf(2, 3, 8, 108),
        listOf(7, 0, 8, 128),
        listOf(4, 2, 8, 144),
        listOf(1, 4, 8, 162),
        listOf(6, 1, 8, 192),
    ),
    listOf(
        listOf(3, 3, 8, 216),
        listOf(0, 5, 8, 243),
        listOf(8, 0, 10, 256),
        listOf(5, 2, 10, 288),
        listOf(2, 4, 10, 324),
    ),
)

fun printDashedLine() {
    println("-----------------------------")
}

fun makeRandomIntArray(size: Int): IntArray {
    val numberArray = IntArray(size) { it }
    repeat(300) {
        val index1 = numberArray.indices.random()
        val index2 = numberArray.indices.random()
        swap(numberArray, index1, index2)
    }
    return numberArray
}

fun swap(array: IntArray, index1: Int, index2: Int) {
    val temp = array[index1]
    array[index1] = array[index2]
    array[index2] = temp
}