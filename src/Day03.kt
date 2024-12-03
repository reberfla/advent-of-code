
fun main(){
    fun part1(input: String): Int {
        val regex = "mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\)".toRegex()
        val captures = regex.findAll(input)
        return captures.map{it.groupValues[1].toInt() * it.groupValues[2].toInt()}.sum()
    }

    fun part2(input: String): Int {
        val split = "do\\(\\)".toRegex()
        val calc = "mul\\((?<first>\\d{1,3}),(?<second>\\d{1,3})\\)".toRegex()
        val splits = input.splitToSequence(split).map{ it.split("don't()")[0]}
        var sum = 0
        for (split in splits){
            val captures = calc.findAll(split)
            sum += captures.map{it.groupValues[1].toInt() * it.groupValues[2].toInt()}.sum()
        }
        return sum
    }

    check(part1(readInputString("Day03_test"))==161)
    check(part2(readInputString("Day03_test2"))==48)
    val input = readInputString("Day03")
    part1(input).println()
    part2(input).println()
}