package com.example.demo.dataclass

fun getStandardKeyboard():HashMap<Char,Pair<Int,Int>> {
    val topKeys = charArrayOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P')
    val middleKeys = charArrayOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L')
    val bottomKeys = charArrayOf('Z', 'X', 'C', 'V', 'B', 'N', 'M')

    var row = 0 //  start with top make an enum of top = 0, center = 1, bottom = 2
    var column = 0
    val layout: HashMap<Char, Pair<Int, Int>> = HashMap(26)
    var placement: Pair<Int, Int>

    topKeys.forEach {
        placement = Pair(row, column)
        layout[it] = placement
        column++

    }
    column = 0
    row++
    middleKeys.forEach {
        placement = Pair(row, column)
        layout[it] = placement
        column++
    }
    column = 0
    row++
    bottomKeys.forEach {
        placement = Pair(row, column)
        layout[it] = placement
        column++
    }
    return layout
}

