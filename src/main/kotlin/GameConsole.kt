package org.example

class GameConsole {
    private var level: Int = 2
    private val voltorbBoard = VoltorbBoard(level)
    private var currentCoin = 0
    private var isBombDetected = false
    private val user = User()

    private fun controlUser() {
        val userChoice = readln()
        when (userChoice) {
            "w", "W" -> user.moveUp()
            "a", "A" -> user.moveLeft()
            "s", "S" -> user.moveDown()
            "d", "D" -> user.moveRight()
            "c", "C" -> checkCard()
        }
    }

    private fun checkCard() {
        val userPos = user.getUserPos()
        val coin = voltorbBoard.flipCard(userPos)
        if (coin == 0) {
            isBombDetected = true
            return
        }
        if (currentCoin == 0) currentCoin = coin else currentCoin *= coin

    }


    fun startGame() {
        currentCoin = 0
        isBombDetected = false
        while (true) {
            showBoard()
            controlUser()
            if (isBombDetected || checkGameCleared()) break
        }
        if (isBombDetected) {
            println("게임에 실패했습니다!\n 메인메뉴로 돌아갑니다.")
        }
        if (checkGameCleared()) {
            println("모든 2와 3을 찾았습니다!")
            println("[획득 코인] : $currentCoin C")
            user.increaseCoin(currentCoin)
        }

    }


    private fun checkGameCleared(): Boolean {
        return currentCoin == voltorbBoard.getTotalCoins()
    }

    private fun showBoard() {
        val userPos = user.getUserPos()
        println("[현재 코인] : $currentCoin C")
        for (row in 0..4) {
            voltorbBoard.printBoardRow(row)
            println()
            if (row != userPos.first) println()
            else {
                repeat(userPos.second) {
                    print("    ")
                }
                println(" ^  ")
            }
        }
        for (col in 0..4) {
            voltorbBoard.showColHint(col)
        }
        println()
        printDashedLine()
    }
}