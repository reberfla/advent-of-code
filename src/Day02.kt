import kotlin.math.abs

fun main() {

    fun checkRow(row: List<Int>): Boolean{
        if (row[0] == row[1]) {
            return false
        }
        val ascending = row[0] < row[1]
        for (idx in 0..<row.size) {
            if(idx == row.size-1){
                return true
            }
            val dist = row[idx + 1] - row[idx]
            if (ascending && dist in 1..3) {
                continue
            } else if (!ascending && dist in -3..-1) {
                continue
            } else break
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val regex = "\\s+".toRegex()
        val rows = input
            .map{ regex.splitToSequence(it).toList().map{it.toInt()}}
        var sum = 0
        for (row in rows) {
            if (checkRow(row)){
                sum++
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val regex = "\\s+".toRegex()
        val rows = input
            .map{ regex.splitToSequence(it).toList().map{it.toInt()}}
        var sum = 0
        for (row in rows) {
            if (checkRow(row)){
                sum++
            } else {
                val variants = mutableListOf<List<Int>>()
                for (i in 0..<row.size){
                    val localVariant = row.toMutableList()
                    localVariant.removeAt(i)
                    variants.add(localVariant.toList())
                }
                for (variant in variants){
                    if(checkRow(variant)){
                        sum++
                        break
                    }
                }
            }
        }
        return sum
    }


    check(part1(readInputLines("Day02_test"))==2)
    check(part2(readInputLines("Day02_test"))==4)
    val input = readInputLines("Day02")
    part1(input).println()
    part2(input).println()
}
