fun main() {
    fun part1(input: List<String>): Int {
        val input = input.map{it.map{it.code-48}}
        val path = hashMapOf<Pair<Int, Int>, List<Pair<Int, Int>>>()
        val startPos = mutableListOf<Pair<Int, Int>>()
        for((y,row) in input.withIndex()){
            for((x,num) in row.withIndex()){
                if(num == 9){
                    startPos.add(Pair(x,y))
                }
                var pos = mutableListOf<Pair<Int, Int>>()
                if(y-1>=0 && input[y-1][x] == num-1) {
                    pos.add(Pair(x,y-1))
                }
                if(x+1<row.size && input[y][x+1] == num-1) {
                    pos.add(Pair(x+1,y))
                }
                if(y+1<input.size && input[y+1][x] == num-1) {
                    pos.add(Pair(x,y+1))
                }
                if(x-1>=0 && input[y][x-1] == num-1) {
                    pos.add(Pair(x-1,y))
                }
                if(pos.size>0){
                    path[Pair(x,y)] = pos
                }
            }
        }
        var ans = 0
        for (pos in startPos){
            var candidates = path[pos]!!.toSet()
            for (i in 0..<8){
                var tmpCandidates = mutableSetOf<Pair<Int,Int>>()
                for(candidate in candidates){
                    if(path.containsKey(candidate)){
                        path[candidate]!!.forEach { tmpCandidates.add(it) }
                    }
                }
                candidates = tmpCandidates
            }
            ans+= candidates.size
        }
        return ans
    }

    fun part2(input: List<String>): Int{
        val input = input.map{it.map{it.code-48}}
        val path = hashMapOf<Pair<Int, Int>, List<Pair<Int, Int>>>()
        val startPos = mutableListOf<Pair<Int, Int>>()
        for((y,row) in input.withIndex()){
            for((x,num) in row.withIndex()){
                if(num == 9){
                    startPos.add(Pair(x,y))
                }
                var pos = mutableListOf<Pair<Int, Int>>()
                if(y-1>=0 && input[y-1][x] == num-1) {
                    pos.add(Pair(x,y-1))
                }
                if(x+1<row.size && input[y][x+1] == num-1) {
                    pos.add(Pair(x+1,y))
                }
                if(y+1<input.size && input[y+1][x] == num-1) {
                    pos.add(Pair(x,y+1))
                }
                if(x-1>=0 && input[y][x-1] == num-1) {
                    pos.add(Pair(x-1,y))
                }
                if(pos.size>0){
                    path[Pair(x,y)] = pos
                }
            }
        }
        var ans = 0
        for (pos in startPos){
            var candidates = path[pos]!!
            for (i in 0..<8){
                var tmpCandidates = mutableListOf<Pair<Int,Int>>()
                for(candidate in candidates){
                    if(path.containsKey(candidate)){
                        path[candidate]!!.forEach { tmpCandidates.add(it) }
                    }
                }
                candidates = tmpCandidates
            }
            ans+= candidates.size
        }
        return ans
    }
    check(part1(readInputLines("Day10_test"))==36)
    check(part2(readInputLines("Day10_test"))==81)
    val input = readInputLines("Day10")
    part1(input).println()
    part2(input).println()
}