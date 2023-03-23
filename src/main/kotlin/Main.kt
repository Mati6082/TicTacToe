import kotlin.random.Random

fun main() {
    val b = Array(9) { 0 }
    var champion:String? = null
    drawBoard(b)
    while (!gameOver(b)) {
        getUserMove(b)
        if (!gameOver(b)) getBotMove(b)
        champion = isWinner(b)
        if (champion != null) break
    }
    print(champion)
}

fun isWinner(b: Array<Int>): String? {
    if (gameOver(b)) {
        return if (checkRows(b) == 3 ||
            checkColumns(b) == 3 ||
            checkDiagonals(b) == 3)
            "Wygrał gracz"
        else if (checkRows(b) == -3 ||
            checkColumns(b) == -3 ||
            checkDiagonals(b) == -3)
            "Wygrał bot"
        else "Remis"
    }
    return null
}

fun gameOver(b: Array<Int>):Boolean {
    if (0 !in b ||
        checkRows(b) != null ||
        checkColumns(b) != null ||
        checkDiagonals(b) != null)
        return true
    return false
}

fun checkRows(b: Array<Int>): Int? {
    var sum = 0
    for (i in 0..8) {
        if (i % 3 == 0 && i != 0)
            sum = 0
        sum += b[i]
        if (sum == 3 || sum == -3) return sum
    }
    return null
}

fun checkColumns(b: Array<Int>): Int? {
    for (i in 0..2) {
        val sum = b[0 + i] + b[3 + i] + b[6 + i]
        if (sum == 3 || sum == -3) return sum
    }
    return null
}

fun checkDiagonals(b: Array<Int>): Int? {
    val diagonal1 = b[0] + b[4] + b[8]
    val diagonal2 = b[2] + b[4] + b[6]
    if (diagonal1 == 3 || diagonal1 == -3) return diagonal1
    if (diagonal2 == 3 || diagonal2 == -3) return diagonal2
    return null
}

fun drawBoard(b: Array<Int>) {
    for (i in 0 until 9) {
        if (i % 3 == 0 && i != 0) print("\n")
        print("${b[i]} ")
    }
    print("\n\n")
}

fun getUserMove(b: Array<Int>) {
    while (true) {
        print("Podaj numer pola: ")
        val userMove = readln().toInt()
        if (userMove !in 1..9) {
            println("\nNieprawidłowa wartość\n")
            continue
        } else if (b[userMove - 1] != 0) {
            println("\nTo pole jest juz zajęte\n")
            continue
        } else {
            b[userMove - 1] = 1
            break
        }
    }
    println()
    drawBoard(b)
}

fun getBotMove(b: Array<Int>) {
    println("Ruch komputera: \n")
    while (true) {
        val computerMove = Random.nextInt(0, 9)
        if (b[computerMove] != 0) continue
        else b[computerMove] = -1; break
    }
    drawBoard(b)
}