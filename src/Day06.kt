import javax.swing.text.Position
import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val input = input.map { StringBuilder(it) }
        var dx = 0
        var dy = -1
        var x = 0
        var y = 0
        for ((yPos, row) in input.withIndex()) {
            if (row.contains("^")) {
                x = row.indexOf("^")
                y = yPos
            }
        }
        while (x in 1..<input[0].length - 1 && y in 1..<input.indices.last) {
            if (input[y + dy][x + dx] == '#') {
                if (dx == 0) {
                    dx = dy * -1
                    dy = 0
                } else {
                    dy = dx
                    dx = 0
                }
            }
            input[y].setCharAt(x, 'X')
            x += dx
            y += dy
        }
        val resString = input.joinToString("")
        return resString.count { it == 'X' } + 1
    }

    fun part2(input: List<String>): Int {
        var ans = 0
        var dx = 0
        var dy = -1
        var xStart = 0
        var yStart = 0
        val path = mutableListOf<Pair<Int, Int>>()
        for ((yPos, row) in input.withIndex()) {
            if (row.contains("^")) {
                xStart = row.indexOf("^")
                yStart = yPos
            }
        }
        var x = xStart
        var y = yStart
        dx = 0
        dy = -1
        while (x in 1..<input[0].length - 1 && y in 1..<input.indices.last) {
            if (input[y + dy][x + dx] == '#') {
                if (dx == 0) {
                    dx = dy * -1
                    dy = 0
                } else {
                    dy = dx
                    dx = 0
                }

            }
            path.add(Pair(x, y))
            x += dx
            y += dy
        }
        path.add(Pair(x,y))

        val positions = mutableListOf<Pair<Int, Int>>()
        for (point in path.slice(1..<path.size)) {
            val localMap = input.map { StringBuilder(it) }
            localMap[point.second].setCharAt(point.first, 'o')
            x = xStart
            y = yStart
            dx = 0
            dy = -1
            while (x in 1..<localMap[0].length - 1 && y in 1..<localMap.indices.last) {
                var replaceChar: Char
                if(dy == 0){
                    replaceChar = if (localMap[y][x] == '|') '+' else '-'
                }else{
                    replaceChar = if (localMap[y][x] == '-') '+' else '|'
                }
                if (localMap[y + dy][x + dx] == '#' || localMap[y + dy][x + dx] == 'o') {
                    replaceChar = '+'
                    if (localMap[y][x] == replaceChar) {
                        positions.add(point)
                        break
                    }
                    replaceChar = '+'
                    if (dx == 0) {
                        dx = dy * -1
                        dy = 0
                    } else {
                        dy = dx
                        dx = 0
                    }
                }
                localMap[y].setCharAt(x, replaceChar)
                if (localMap[y + dy][x + dx] == '#' || localMap[y + dy][x + dx] == 'o') {
                    if (dx == 0) {
                        dx = dy * -1
                        dy = 0
                    } else {
                        dy = dx
                        dx = 0
                    }
                }
                x += dx
                y += dy
            }

        }
        return positions.toSet().size
    }

    check(part1(readInputLines("Day06_test"))==41)
    check(part2(readInputLines("Day06_test"))==6)
    val input = readInputLines("Day06")
    part1(input).println()
    part2(input).println()
}