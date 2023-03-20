import kotlin.random.Random
import kotlin.reflect.typeOf

fun main(args: Array<String>) {
    var board = Array(9) { 0 }
    drawBoard(board)
    getUserMove(board)
    getBotMove(board)
}

fun checkBoard(board: Array<Int>) {
    checkRows(board)
}

fun checkRows(board: Array<Int>):Int? {
    var sum = 0
    for (i in 0 .. 8) {
        if (i % 3 == 0 && i != 0)
            sum = 0
        else sum += board[i]
        if (sum == 3 || sum == 6) return sum
    }
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
        } else if (board[userMove-1] != 0) {
            println("\nTo pole jest juz zajete\n")
            continue
        } else {
            board[userMove-1] = 1
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
        else board[computerMove] = 2; break
    }
    drawBoard(board)
}