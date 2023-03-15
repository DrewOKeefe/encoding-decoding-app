package chucknorris

fun menu() {
    println("Please input operation (encode/decode/exit):")
    return when (val input = readln()) {
        "encode" -> encode()
        "decode" -> decode()
        "exit" -> {
            return println("Bye!")
        }
        else -> {
            println("There is no '$input' operation\n")
            menu()
        }
    }
}
fun encode() {
    println("Input string:")
    val input = readln()
    var binary = ""
    for (char in input) {
        binary += String.format("%07d",Integer.toBinaryString(char.code).toInt())
    }
    var result = ""
    var holder = ' '
    for (i in binary) {
        if (i != holder) {
            result += if (i == '0') " 00 " else " 0 "
            holder = i
        }
        result += '0'
    }
    println("Encoded string:\n${result.trim()}")
    menu()
}
fun decode() {
    println("Input encoded string:")
    val input = readln().trim().split(" ").chunked(2)
    var binary = ""
    for (i in input) {
        if (i[0] == "0") {
            if (i.size == 2) binary += "1".repeat(i[1].length)
        } else if (i[0] == "00") {
            if (i.size == 2) binary += "0".repeat(i[1].length)
        } else {
            println("Encoded string is not valid.\n")
            return menu()
        }
    }
    var result = ""
    for (chunk in binary.chunked(7)) {
        if(chunk.length != 7) {
            println("Encoded string is not valid.\n")
            return menu()
            } else result += (Char(chunk.toInt(2)))
        }
    println("Decoded string:\n$result\n")
    menu()
}

fun main() {
    menu()
}