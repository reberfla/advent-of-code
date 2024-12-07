import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Long {
        val numbers = """\d+""".toRegex()
        val rows = input.map{ it.split(":")}
        var ans = 0L
        for (row in rows){
            val target  = row[0].toLong()
            val calc = numbers.findAll(row[1]).map { it.value.toLong() }.toMutableList()
            val combinations = mutableListOf<String>()
            val range = 2.0.pow(calc.size-1)-1
            for (i in 0..range.toLong()){
                combinations.add(i.toString(2).padStart(calc.size-1, '0'))
            }
            for (combination in combinations.reversed()){
                var tmp = calc[0]
                for((idx, operation) in combination.withIndex()){
                    if (operation == '0') {
                        tmp += calc[idx+1]
                    }else {
                        tmp *= calc[idx + 1]
                    }
                }
                if (tmp == target){
                    ans += target
                    break
                }
            }
        }
        return ans
    }

    fun part2(input: List<String>): Long {
        val numbers = """\d+""".toRegex()
        val rows = input.map{ it.split(":")}
        var ans = 0L
        for (row in rows) {
            val target = row[0].toLong()
            val calc = numbers.findAll(row[1]).map { it.value.toLong() }.toMutableList()
            val range = 3.0.pow(calc.size-1)-1
            val combinations = mutableListOf<String>()
            for (i in 0..range.toLong()){
                combinations.add(i.toString(3).padStart(calc.size-1, '0'))
            }
            combination@ for (combination in combinations.reversed()) {
                var tmp = calc[0]
                for ((idx, operation) in combination.withIndex()) {
                    if (operation == '0') {
                        tmp += calc[idx + 1]
                    } else if (operation == '1') {
                        tmp *= calc[idx + 1]
                    } else {
                        tmp = "$tmp${calc[idx+1]}".toLong()
                    }
                }
                if (tmp == target) {
                    ans += target
                    break@combination
                }
            }
        }
        return ans
    }
    check(part1(readInputLines("Day07_test"))==3749L)
    check(part2(readInputLines("Day07_test"))==11387L)
    val input = readInputLines("Day07")
    part1(input).println()
    part2(input).println()
}