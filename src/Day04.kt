
fun main(){
    fun part1(input: String): Int {
        val searchString = mutableListOf<String>()
        val regex = """(?=(XMAS|SAMX))""".toRegex()
        val rows = input.trim().lines()
        for (row in rows){
            searchString.add(row)
        }
        for ( col in 0..<rows[0].length){
            var tmp = ""
            for (row in rows.indices){
                tmp+=rows[row][col]
            }
            searchString.add(tmp)
        }

        var diagonals = Array(rows.size*2-1) { "" }
        for ((idx,row) in rows.withIndex()){
            for ((i, el) in row.withIndex()) diagonals[idx+i] = diagonals[idx+i] + el
        }
        diagonals.map{searchString.add(it)}
        diagonals = Array(rows.size*2-1) { "" }
        for ((idx,row) in rows.withIndex()){
            for ((i,el) in row.reversed().withIndex()) diagonals[idx+i] = diagonals[idx+i] + el
        }
        diagonals.map{searchString.add(it)}
        return regex.findAll(searchString.joinToString(" ")).count()
    }

    fun part2(input: String): Int {
        val rows = input.trim().lines()
        var sum = 0
        val regex = """MAS|SAM""".toRegex()
        for ((rowIdx, row) in rows.withIndex()){
            if (rowIdx == 0 || rowIdx == rows.size-1){
                continue
            }
            for ((colIdx, col) in row.withIndex()){
                if (colIdx == 0 || colIdx == row.length-1){
                    continue
                }
                if (col == 'A'){
                    val diag1 = "${rows[rowIdx-1][colIdx-1]}$col${rows[rowIdx+1][colIdx+1]}"
                    val diag2 = "${rows[rowIdx+1][colIdx-1]}$col${rows[rowIdx-1][colIdx+1]}"
                    if (regex.matches(diag1) && regex.matches(diag2)){
                        sum++
                    }
                }
            }
        }
        return sum
    }

    check(part1(readInputString("Day04_test"))==18)
    check(part2(readInputString("Day04_test2"))==9)
    val input = readInputString("Day04")
    part1(input).println()
    part2(input).println()
}