import kotlin.random.Random
import kotlin.random.nextInt

var board = arrayListOf<ArrayList<String>>()

fun main() {
    for (i in 0..2) {
        val row = arrayListOf<String>()
        for (j in 0..2)
            row.add("")
        board.add(row)
    }
    printBoard()

    var continueGame = true
    do {
        println("PLease enter a position 1-3 for row and column (i.e. 1,1)")
        val playerInput = readLine()?:""
        var x = 0
        var y = 0
        var skipRound = false

        try{
            val positions = playerInput.split(",")
            x = positions[0].trim().toInt()
            y = positions[1].trim().toInt()

            if (board[x-1][y-1] != "") {
                println("Position $playerInput is already taken. Please try enter a valid position")
                skipRound = true
            } else {
            board[x - 1][y - 1] = "x"

        }

        if(!skipRound) {
            val playerWon = checkWinner(true)
            if (playerWon) {
                println("Congrats! You win!")
                continueGame = false
            }

            val boardFull = checkForTie()
            if(!playerWon && boardFull) {
                println("It's a tie!")
                continueGame = false
            }

            if (continueGame) {
                computerPlacement()
                printBoard()
                val computerWon = checkWinner(false)
                if (computerWon) {
                    println("Computer wins!")
                    continueGame = false
                }
            }
        }

        } catch(e: Exception){
            println("Invalid input")
        }
    } while (continueGame)
}

fun printBoard() {
    println("-----------")
    for (i in 0..2) {
        for (j in 0..2) {
            when(board[i][j]) {
                "x" -> print("| x ")
                "o" -> print("| o ")
                else -> print("|  ")
            }
        }
        println("|")
        println("-----------")
    }
}

fun checkWinner(player: Boolean): Boolean {
    var won = false
    val checkSymbol = if(player) "x" else "o"
    for(i in 0..2) {
        //Horizontal wins
        if (board[i][0] == checkSymbol && board[i][1] == checkSymbol && board[i][2] == checkSymbol) {
            won = true
            break
        }
        //Vertical wins
        if (board[0][i] == checkSymbol && board[1][i] == checkSymbol && board[2][i] == checkSymbol) {
            won = true
            break
        }
    }
        //Diagonal wins
        if(board[0][0] == checkSymbol && board[1][1] == checkSymbol && board[2][2] == checkSymbol) {
            won = true
        }
        if(board[2][0] == checkSymbol && board[1][1] == checkSymbol && board[0][2] == checkSymbol) {
            won = true
    }
    return won
}

fun checkForTie(): Boolean {
    var boardFull = true
    for(i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] == "") {
                boardFull = false
                break
            }
        }
    }
    return boardFull
}

fun computerPlacement() {
    var i = 0
    var j = 0
    do {
        i = Random.nextInt(0 until 3)
        j = Random.nextInt(0 until 3)
    } while (board[i][j] != "")
    board[i][j] = "o"
}