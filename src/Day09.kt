fun main() {
    //very slow solution for part 1
    //had a faster one that only failed due to using Int instead of Long for the sum
    //therefore deleted it :/
    fun part1(input: String): Long {
        val res = mutableListOf<Int>()
        for ((idx,char) in input.withIndex()){
            for(i in 0..<char.code-48)
                if(idx%2 == 0){
                    res.add(idx/2)
                } else {
                    res.add(-1)
                }

        }
        while(res.contains(-1)){
            val last = res.removeAt(res.lastIndexOf(res.findLast{ it > -1}!!))
            res[res.indexOf(-1)] = last
        }
        var ans = 0L
        for ((idx, num) in res.withIndex()){
          ans += num * idx
        }
        return ans
    }

    fun part2(input: String): Long{
        val numbers = hashMapOf<Int, List<Int>>()
        val gaps = hashMapOf<Int, Int>()
        var index = 0
        for ((idx,char) in input.withIndex()){

            if(idx%2 == 0){
                numbers[index] =  List(char.code-48){idx/2}
                index+= char.code-48
            } else {
                gaps[index] = char.code-48
                index+= char.code-48
            }
        }

        var sortedGaps = gaps.keys.sorted()
        for (number in numbers.keys.sorted().reversed()){
            val value = numbers[number]!!
            for (idx in sortedGaps){
                val gapSize = gaps[idx]!!
                if(gapSize >= value.size && idx < number){
                    numbers.remove(number)
                    gaps.remove(idx)
                    gaps[idx+value.size] = gapSize - value.size
                    numbers[idx] = value
                    sortedGaps = gaps.keys.sorted()
                    break
                }
            }
        }

        var ans = 0L
        for ((k,v) in numbers){
            for ((dx, value) in v.withIndex()){
               ans+= ((k+dx)*value)
            }
        }
        return ans
    }
    check(part1(readInputString("Day09_test"))==1928L)
    check(part2(readInputString("Day09_test"))==2858L)
    val input = readInputString("Day09")
    part1(input).println()
    part2(input).println()
}