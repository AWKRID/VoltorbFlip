package org.example

class GameConsole {
    var level: Int = 1
    private val voltorbBoard = VoltorbBoard(level)
    private val user = User()


    fun showBoard() {
        for (row in 0..4) {
            voltorbBoard.printBoardRow(row)
            println()
        }
        for(col in 0 .. 4){
            voltorbBoard.showColHint(col)
        }
        println()
        printDashedLine()
    }


}