package org.example

import kotlin.math.max

class GameConsole {
    private var level: Int = 1
    private var voltorbBoard = VoltorbBoard(level)
    private var currentCoin = 0
    private var isBombDetected = false
    private var isCleared = false
    private var isStop = false
    private var flipCount = 0
    private val user = User()


    fun run() {
        while (true) {
            printMenu()
            when (readln()) {
                "1" -> startGame()
                "2" -> tutorialGame()
                "3" -> tutorialMove()
                "4" -> return
                else -> {
                    println("올바르지 않은 입력입니다.")
                    continue
                }
            }
        }
    }

    private fun initGame() {
        voltorbBoard = VoltorbBoard(level)
        isBombDetected = false
        flipCount = 0
        currentCoin = 0
        isCleared = false
    }

    private fun printMenu() {
        println("찌리리공 뒤집기 게임에 오신 것을 환영합니다!")
        println("찌리리공 뒤집기 Lv. ${level}로 플레이 하시겠습니까?")
        println("1. 플레이한다.")
        println("2. 게임 설명 보기")
        println("3, 조작법 설명 보기")
        println("4. 종료하기")
        printDashedLine()
    }

    private fun startGame() {
        initGame()
        showBoard()
        while (true) {
            controlUser()
            if (isStop) return
            showBoard()
            if (isBombDetected) {
                showFailMessage()
                break
            }
            if (isCleared) {
                showClearMessage()
                user.increaseCoin(currentCoin)
                break
            }
        }
        setLevel()
    }

    private fun tutorialGame() {
        println("<게임 설명 보기>")
        printDashedLine()
        println("- 찌리리공 뒤집기는 카드를 뒤집어 숫자를 찾아내는 게임입니다.")
        println("- 카드에는 1~3까지 숫자와 찌리리공(0)이 숨어 있습니다. ")
        println("- 각 행에 대한 힌트는 우측에, 각 열에 대한 힌트는 하단에 표기되어 있습니다.\nHint: [전체 숫자 합/찌리리공 개수]")
        println("- 가장 먼저 뒤집은 숫자는 획득 동전에 더해지며,\n그 이후 뒤집은 숫자는 획득 동전에 곱해집니다.")
        println("- 만약 찌리리공 카드를 뒤집은 경우 게임이 종료되며\n해당 게임에서 얻은 동전을 모두 잃습니다.")
        println("- 숨겨져 있는 2와 3의 숫자를 모두 찾으면 게임을 승리합니다!")
        printDashedLine()
    }

    private fun tutorialMove() {
        println("<조작법 설명 보기>")
        printDashedLine()
        println("WASD키를 활용하여 게임 보드를 이동할 수 있습니다.")
        println("원하는 방향의 키를 입력 후 Enter키를 누르면 이동합니다.")
        println("현재 자리의 카드를 확인하고 싶은 경우 C를 입력하여 확인합니다.")
        println("현재 게임을 종료하고 싶은 경우 QUIT을 입력해주세요")
        printDashedLine()
    }

    private fun showLevelAndUserCoin() {
        println("찌리리공 뒤집기 Lv. $level")
        printDashedLine()
        println("수집한 코인: ${user.getUserCoin()} C")
        println("현재 게임에서 모은 코인: $currentCoin")
        printDashedLine()
    }

    private fun showBoard() {
        showLevelAndUserCoin()
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

    private fun controlUser() {
        val userChoice = readln()
        when (userChoice) {
            "w", "W" -> user.moveUp()
            "a", "A" -> user.moveLeft()
            "s", "S" -> user.moveDown()
            "d", "D" -> user.moveRight()
            "c", "C" -> checkCard()
            "QUIT" -> stopGame()
        }
    }

    private fun setLevel() {
        if (isCleared) increaseLevel()
        else decreaseLevel()
    }

    private fun checkCard() {
        val userPos = user.getUserPos()
        val coin = voltorbBoard.flipCard(userPos)
        if (coin == 0) {
            isBombDetected = true
            return
        }
        if (currentCoin == 0) currentCoin = coin else currentCoin *= coin
        flipCount++
        checkGameCleared()
    }


    private fun increaseLevel() {
        val maxLevel = boardRules.size
        if (level == maxLevel) return
        level++
        println("게임 레벨이 Lv. ${level}으로 올랐습니다.\n다음 게임에서 받을 수 있는 동전이 늘어납니다!")
        printDashedLine()
    }

    private fun decreaseLevel() {
        if (flipCount >= level) return
        level = max(flipCount, 1)
        println("게임 레벨이 Lv. ${level}로 내려갔습니다.")
        printDashedLine()
    }

    private fun showFailMessage() {
        println("게임에 실패했습니다!\n 메인메뉴로 돌아갑니다.")
        printDashedLine()
    }

    private fun showClearMessage() {
        println("게임 클리어~!!")
        println("숨어 있던 2와 3의 카드를 모두 찾았습니다!")
        println("$currentCoin C을 획득했습니다!")
        printDashedLine()
    }

    private fun stopGame() {
        println("현재 게임을 종료합니다.")
        println("$currentCoin C을 획득했습니다.")
        printDashedLine()
        isStop = true
    }

    private fun checkGameCleared() {
        isCleared = currentCoin == voltorbBoard.getTotalCoins()
    }


}