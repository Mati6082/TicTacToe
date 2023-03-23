import kotlin.random.Random

fun main(args: Array<String>) {
    var board = Array(9) { 0 }
    drawBoard(board)
    getUserMove(board)
    getBotMove(board)
}

fun gameOver(board: Array<Int>):Boolean {
    if (0 in board &&
        checkRows(board) != null &&
        checkColumns(board) != null &&
        checkDiagonals(board) != null)
        return true
    return false
}

fun checkRows(board: Array<Int>): Int? {
    var sum = 0
    for (i in 0..8) {
        if (i % 3 == 0 && i != 0)
            sum = 0
        sum += board[i]
        if (sum == 3 || sum == -3) return sum
    }
    return null
}

fun checkColumns(board: Array<Int>): Int? {
    for (i in 0..2) {
        val sum = board[0 + i] + board[3 + i] + board[6 + i]
        if (sum == 3 || sum == -3) return sum
    }
    return null
}

fun checkDiagonals(board: Array<Int>): Int? {
    val diagonal1 = board[0] + board[4] + board[8]
    val diagonal2 = board[2] + board[4] + board[6]
    if (diagonal1 == 3 || diagonal1 == -3) return diagonal1
    if (diagonal2 == 3 || diagonal2 == -3) return diagonal2
    return null
}

fun drawBoard(board: Array<Int>) {
    for (i in 0 until 9) {
        if (i % 3 == 0 && i != 0) print("\n")
        print("${board[i]} ")
    }
    print("\n\n")
}

fun getUserMove(board: Array<Int>) {
    while (true) {
        print("Podaj numer pola: ")
        val userMove = readln().toInt()
        if (userMove !in 1..9) {
            println("\nNieprawidlowa wartosc\n")
            continue
        } else if (board[userMove - 1] != 0) {
            println("\nTo pole jest juz zajete\n")
            continue
        } else {
            board[userMove - 1] = 1
            break
        }
    }
    println()
    drawBoard(board)
}

fun getBotMove(board: Array<Int>) {
    println("Ruch komputera: \n")
    while (true) {
        val computerMove = Random.nextInt(0, 9)
        if (board[computerMove] != 0) continue
        else board[computerMove] = -1; break
    }
    drawBoard(board)
}