fun main() {
    fun part1(input: String): Int {
        var input = input.split(" ").map{it.toLong()}.toMutableList()
        for (i in 0..<25){
            val tmp = input.toMutableList()
            var idx = 0
            for (num in input){
                if (num == 0L){
                    tmp[idx] = 1L
                    idx++
                }else if (num.toString().length % 2 == 0) {
                    val numString = num.toString()
                    tmp[idx] = numString.slice(numString.length / 2..<numString.length).toLong()
                    tmp.add(idx, numString.slice(0..<numString.length / 2).toLong())
                    idx+=2
                } else {
                    tmp[idx] = num*2024
                    idx++
                }
            }
            input = tmp
        }
        return input.size
    }

    // needed some help on this one :/
    fun part2(input: String): Long{
        val seen = mutableMapOf<Pair<Long, Long>, Long>()
        fun check(x:Long , n:Long): Long{
            if (n == 0L){
                return 1
            }
            if (!seen.containsKey(Pair(x,n))){
                var res = 0L
                if (x == 0L){
                    res = check(1, n-1)
                }else if (x.toString().length % 2 == 0){
                    val numString = x.toString()
                    res += check(numString.slice(numString.length / 2..<numString.length).toLong(), n-1)
                    res += check(numString.slice(0..<numString.length / 2).toLong(), n-1)
                }else{
                    res = check(2024 * x, n-1)
                }
                seen[Pair(x,n)] = res
            }
            return seen[Pair(x, n)]!!
        }
        var ans = 0L
        for (num in input.split(" ").map{it.toLong()}){
            ans += check(num, 75L)
        }
        return ans
    }
    check(part1(readInputString("Day11_test"))==55312)
    val input = readInputString("Day11")
    part1(input).println()
    part2(input).println()
}