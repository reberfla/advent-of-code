
fun main(){
    fun part1(input: String): Int {
        var ans = 0
        val (order, updates) = input.split("\n\n")
        val regex = "\\s+".toRegex()
        val orderMap = hashMapOf<String,MutableList<String>>()
        regex.splitToSequence(order).forEach {
            val (k, v) = it.split("|")
            if (orderMap.containsKey(k)) {
                orderMap[k]!!.add(v)
            }else{
                orderMap[k] = mutableListOf(v)
            } }
        val updateSequence = updates.split("\n").map{it.split(",")}
        var checked = mutableListOf<String>()
        for (row in updateSequence){
            for ((idx, num) in row.withIndex()){
                if(orderMap.containsKey(num) && orderMap[num]!!.any{it in checked}){
                    checked = mutableListOf()
                    break
                }else if (idx == row.indices.last) {
                    ans += row[row.size/2].toInt()
                    checked = mutableListOf()
                }else {
                    checked += num
                }
            }
        }
        return ans
    }

    fun part2(input: String): Int {
        var ans = 0
        val (order, updates) = input.split("\n\n")
        val regex = "\\s+".toRegex()
        val orderMap = hashMapOf<String,MutableList<String>>()
        regex.splitToSequence(order).forEach {
            val (k, v) = it.split("|")
            if (orderMap.containsKey(k)) {
                orderMap[k]!!.add(v)
            }else{
                orderMap[k] = mutableListOf(v)
            } }
        val updateSequence = updates.split("\n").map{it.split(",")}
        var checked = mutableListOf<String>()
        for (row in updateSequence){
            var newOrder = false
            for ((idx, num) in row.withIndex()){
                if(orderMap.containsKey(num) && orderMap[num]!!.any{it in checked}){
                    val wrongOrder = orderMap[num]!!.filter{it in checked}
                    val insertionIdx = checked.first{it in wrongOrder}
                    checked.add(checked.indexOf(insertionIdx), num)
                    newOrder = true
                    if(idx == row.indices.last){
                        ans += checked[checked.size/2].toInt()
                        checked = mutableListOf()
                        newOrder = false
                    }
                }else if (idx == row.indices.last && newOrder) {
                    checked += num
                    ans += checked[checked.size/2].toInt()
                    checked = mutableListOf()
                    newOrder = false
                }else if (idx == row.indices.last && !newOrder){
                    checked = mutableListOf()
                }else {
                    checked += num
                }
            }
        }
        return ans
    }

    check(part1(readInputString("Day05_test"))==143)
    check(part2(readInputString("Day05_test"))==123)
    val input = readInputString("Day05")
    part1(input).println()
    part2(input).println()
}