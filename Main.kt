package chucknorris

fun main() {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        val operation = readln()
        when (operation) {
            "encode" -> encode()
            "decode" -> decode()
            "exit" -> {
                println("Bye!")
                return
            }
            else -> {
                println("There is no '$operation' operation\n")
            }
        }
    }
}

fun encode() {
    println("Input string:")
    val input = readln()
    var binaryStr = ""
    for (i in input) {
        binaryStr += Integer.toBinaryString(i.code).padStart(7, '0')
    }
    var cipher = ""
    for (i in binaryStr.indices) {
        if (i == 0 && binaryStr[0] == '1') {
            cipher += "0 0"
        } else if (i == 0 && binaryStr[0] == '0') {
            cipher += "00 0"
        }
        if (i != 0) {
            cipher += if (binaryStr[i] == binaryStr[i - 1]) {
                "0"
            } else {
                if (binaryStr[i] == '1') {
                    " 0 0"
                } else {
                    " 00 0"
                }
            }
        }
    }
    println(String.format("Encoded string:%n%s", cipher))
}

fun decode() {
    println("Input encoded string:")
    val input = readln()
    for (i in 0 until input.length) {
        if (input[i] != '0' && input[i] != ' ') {
            println("Encoded string is not valid.\n")
            return
        }
    }
    val inputList = input.split(" ")
    var binaryStr = ""
    for (i in 0 until inputList.size - 1) {
        if (i % 2 == 0 && inputList[i] != "0" && inputList[i] != "00") {
            println("Encoded string is not valid.\n")
            return
        } else if (inputList.size % 2 != 0) {
            println("Encoded string is not valid.\n")
            return
        } else if (i % 2 == 0 && inputList[i] == "0") {
            binaryStr += inputList[i + 1].replace("0", "1")
        } else if (i % 2 == 0 && inputList[i] == "00") {
            binaryStr += inputList[i + 1]
        }
    }
    if (binaryStr.length % 7 != 0) {
        println("Encoded string is not valid.\n")
        return
    }
    println("Decoded string:")
    while (binaryStr.length >= 7) {
        val subStr = binaryStr.substring(0, 7)
        print(subStr.toInt(2).toChar())
        binaryStr = binaryStr.substring(7)
    }
    println()
}

