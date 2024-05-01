package org.example

class GameConsole {
    private var level: Int = 2
    private val voltorbBoard = VoltorbBoard(level)
    private var currentCoin = 0
    private var isBombDetected = false
    private val user = User()



    fun run(){
        while(true) {
            printMenu()
            when (readln()) {
                "1" -> startGame()
                "2" -> tutorial()
                "3" -> return
                else -> {
                    println("올바르지 않은 입력입니다.")
                    continue
                }
            }
        }
    }



    private fun printMenu(){
        println("찌리리공 뒤집기 게임에 오신 것을 환영합니다!")
        println("찌리리공 뒤집기 Lv. $level 을 플레이 하시겠습니까?")
        println("1. 플레이한다.")
        println("2. 게임 설명 보기")
        println("3. 종료하기")
        printDashedLine()
    }

    private fun tutorial(){
        println("<게임 설명 보기>")
        printDashedLine()
        println("- 찌리리공 뒤집기는 카드를 뒤집어 숫자를 찾아내는 게임입니다.")
        println("- 카드에는 1~3까지 숫자와 찌리리공(0)이 숨어 있습니다. ")
        println("- 가장 먼저 뒤집은 숫자는 획득 동전에 더해지며,\n그 이후 뒤집은 숫자는 획득 동전에 곱해집니다.")
        println("- 만약 찌리리공 카드를 뒤집은 경우 게임이 종료되며\n해당 게임에서 얻은 동전을 모두 잃습니다.")
        println("- 숨겨져 있는 2와 3의 숫자를 모두 찾으면 게임을 승리합니다!")
        printDashedLine()
    }

    private fun showLevelAndUserCoin(){
        println("찌리리공 뒤집기 Lv. $level")
        printDashedLine()
        println("수집한 코인: ${user.getUserCoin()} C")
        println("현재 게임에서 모은 코인: $currentCoin")
        printDashedLine()
    }

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


    private fun startGame() {
        currentCoin = 0
        isBombDetected = false
        while (true) {
            showLevelAndUserCoin()
            showBoard()
            controlUser()
            if (isBombDetected || checkGameCleared()) break
        }
        showBoard()
        if (isBombDetected) {
            println("게임에 실패했습니다!\n 메인메뉴로 돌아갑니다.")
        }
        if (checkGameCleared()) {
            println("모든 2와 3을 찾았습니다!")
            println("$currentCoin C을 획득했습니다!")
            user.increaseCoin(currentCoin)
            printDashedLine()
        }

    }


    private fun checkGameCleared(): Boolean {
        return currentCoin == voltorbBoard.getTotalCoins()
    }

    private fun showBoard() {
        val userPos = user.getUserPos()
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