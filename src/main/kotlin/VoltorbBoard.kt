package org.example


class VoltorbBoard(level: Int) {
    private var board: Array<Array<Pair<Int, Boolean>>> = Array(5) { Array(5) { Pair(1, false) } }
    private val rowHint: Array<Pair<Int, Int>> = Array(5) { Pair(0, 0) }
    private val colHint: Array<Pair<Int, Int>> = Array(5) { Pair(0, 0) }
    private var totalCoin = 0

    init {
        makeBoard(level)
        setHint()
    }

    private fun showRowHint(row: Int) {
        print("${rowHint[row].first}/${rowHint[row].second}")
    }

    fun showColHint(col: Int) {
        print("${colHint[col].first}/${colHint[col].second} ")
    }

    fun flipCard(pos: Pair<Int, Int>): Int {
        if (board[pos.first][pos.second].second) return 1
        board[pos.first][pos.second] = board[pos.first][pos.second].copy(second = true)
        return board[pos.first][pos.second].first
    }

    fun printBoardRow(row: Int) {
        for (i in 0..4) {
            printCard(board[row][i])
        }
        showRowHint(row)
    }

    fun getTotalCoins(): Int {
        return totalCoin
    }


    private fun printCard(card: Pair<Int, Boolean>) {
        if (!card.second) print(" x  ")
        else print(" ${card.first}  ")
    }

    private fun makeBoard(level: Int) {
        val boardInfo = boardRules[level - 1].random()
        totalCoin = boardInfo[3]
        val randomArr = makeRandomIntArray(25)
        var idx = 0
        repeat(boardInfo[0]) {
            board[randomArr[idx] / 5][randomArr[idx] % 5] = Pair(2, false)
            idx++
        }
        repeat(boardInfo[1]) {
            board[randomArr[idx] / 5][randomArr[idx] % 5] = Pair(3, false)
            idx++
        }
        repeat(boardInfo[2]) {
            board[randomArr[idx] / 5][randomArr[idx] % 5] = Pair(0, false)
            idx++
        }
    }

    private fun setHint() {
        for (row in 0..4) {
            var coinCount = 0
            var bombCount = 0
            board[row].forEach {
                val coin = it.first
                if (coin > 0) coinCount += coin
                else bombCount++
            }
            rowHint[row] = Pair(coinCount, bombCount)
        }

        for (col in 0..4) {
            var coinCount = 0
            var bombCount = 0
            for (row in 0..4) {
                val coin = board[row][col].first
                if (coin == 0) bombCount++
                else coinCount += coin
            }
            colHint[col] = Pair(coinCount, bombCount)
        }

    }
}