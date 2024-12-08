operator fun Pair<Int, Int>.minus(other: Pair<Int,Int>): Pair<Int, Int> = Pair(first - other.first, second - other.second)
operator fun Pair<Int, Int>.plus(other: Pair<Int,Int>): Pair<Int, Int> = Pair(first + other.first, second + other.second)

fun main() {
    fun part1(input: List<String>): Int {
        val regex = """\d|[A-Z]|[a-z]""".toRegex()
        val antennas = hashMapOf<Char, MutableList<Pair<Int, Int>>>()
        for ((y, row) in input.withIndex()){
            for ((x, char) in row.withIndex()){
                if(regex.matches(char.toString()))
                if(antennas.keys.contains(char)){
                    antennas[char]!!.add(Pair(x,y))
                } else {
                    antennas[char] = mutableListOf(Pair(x,y))
                }
            }
        }
        val freqPos = mutableSetOf<Pair<Int, Int>>()
        for(positions in antennas.values){
            for(start in 0..< positions.size-1){
                val left = positions[start]
                for (other in start+1..<positions.size){
                    val right = positions[other]
                    val dist = left-right
                    val first = left + dist
                    if(first.first in 0..<input[0].length && first.second in input.indices){
                        freqPos.add(first)
                    }
                    val second = right - dist
                    if(second.first in 0..<input[0].length && second.second in input.indices){
                        freqPos.add(second)
                    }
                }
            }
        }
        return freqPos.size
    }

    fun part2(input: List<String>): Int {
        val regex = """\d|[A-Z]|[a-z]""".toRegex()
        val antennas = hashMapOf<Char, MutableList<Pair<Int, Int>>>()
        for ((y, row) in input.withIndex()){
            for ((x, char) in row.withIndex()){
                if(regex.matches(char.toString()))
                    if(antennas.keys.contains(char)){
                        antennas[char]!!.add(Pair(x,y))
                    } else {
                        antennas[char] = mutableListOf(Pair(x,y))
                    }
            }
        }
        val freqPos = mutableSetOf<Pair<Int, Int>>()
        for(positions in antennas.values){
            for(start in 0..< positions.size-1){
                val left = positions[start]
                for (other in start+1..<positions.size){
                    val right = positions[other]
                    val dist = left-right
                    var first = left + dist
                    freqPos.add(left)
                    freqPos.add(right)
                    while(first.first in 0..<input[0].length && first.second in input.indices){
                        freqPos.add(first)
                        first += dist
                    }
                    var second = right - dist
                    while(second.first in 0..<input[0].length && second.second in input.indices){
                        freqPos.add(second)
                        second -= dist
                    }
                }
            }
        }
        return freqPos.size
    }
    check(part1(readInputLines("Day08_test"))==14)
    check(part2(readInputLines("Day08_test"))==34)
    val input = readInputLines("Day08")
    part1(input).println()
    part2(input).println()
}