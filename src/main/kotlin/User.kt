package org.example

class User {
    private var yPos = 0
    private var xPos = 0
    private var coin = 0
    fun moveUp() {
        if (yPos > 0) yPos--
    }

    fun moveDown() {
        if (yPos < 4) yPos++
    }

    fun moveLeft() {
        if (xPos > 0) xPos--
    }

    fun moveRight() {
        if (xPos < 4) xPos++
    }

    fun increaseCoin(rewards: Int) {
        coin += rewards
    }

    fun getUserPos(): Pair<Int, Int> {
        return Pair(yPos, xPos)
    }

    fun getUserCoin(): Int {
        return coin
    }
    fun resetPos(){
        yPos = 0
        xPos = 0
    }
}