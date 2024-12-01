import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val leftCol = mutableListOf<Int>()
        val rightCol = mutableListOf<Int>()
        val regex = "\\s+".toRegex()
        input
            .map{ regex.splitToSequence(it, 2)}
            .forEach { leftCol.add(it.first().toInt()); rightCol.add(it.last().toInt()) }
        leftCol.sort()
        rightCol.sort()
        var sum = 0
        leftCol.zip(rightCol).forEach {
            row -> sum += abs(row.component1() - row.component2())
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val leftCol = mutableListOf<Int>()
        val rightCol = hashMapOf<Int, Int>()
        val regex = "\\s+".toRegex()
        input
            .map{ regex.splitToSequence(it, 2)}
            .forEach { leftCol.add(it.first().toInt())
            val rightValue = it.last().toInt()
            if (rightCol.keys.contains(rightValue)){
                rightCol[rightValue] = rightCol[rightValue]!! + 1
            } else {
                rightCol[rightValue] = 1
            }}
        return leftCol.sumOf{if(rightCol.keys.contains(it)){ it * rightCol[it]!!} else 0}
    }

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
