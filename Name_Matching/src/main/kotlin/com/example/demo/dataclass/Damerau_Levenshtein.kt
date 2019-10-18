package com.example.demo.dataclass

fun getDHSimilarity(first: String, Second:String, calculateSwappedCount:Boolean): Int {
    val maxDist = first.length + Second.length

    val matrix = Array(first.length + 1) { IntArray(Second.length + 1) }

    for (i in first.indices) {
        matrix[i + 1][1] = i
        matrix[i + 1][0] = maxDist
    }

    for (i in Second.indices) {
        matrix[1][i + 1] = i
        matrix[0][i + 1] = maxDist
    }

    val dA = IntArray(maxDist)

    var swappedCharsAndCount = HashMap<String,Int>()

    for (i in 1 until first.length) {
        var db = 0

        for (j in 1 until Second.length) {

            val i1 = dA[Second.indexOf(Second[j - 1])]
            val j1 = db
            val d = if (first[i - 1] == Second[j - 1])  // Checking if the characters were swapped
            // has to be a string > 1 size to check because you can't swap a single char
            {0}
            else {
                1
            }
            if (d == 0) {
                db = j
            }
            else{ // let's keep track of what values were swapped and how many times
                val key = first[i - 1].toString()+Second[j - 1].toString()
                if(calculateSwappedCount && swappedCharsAndCount.containsKey(key)){
                    var instances = swappedCharsAndCount.getValue(key)
                    swappedCharsAndCount.remove(key)
                    swappedCharsAndCount[key] = instances+1
                }
                else if(calculateSwappedCount) {
                    swappedCharsAndCount[key] = 1
                }
            }

            matrix[i + 1][j + 1] = (matrix[i][j] + d)
                    .coerceAtMost(matrix[i + 1][j] + 1)
                    .coerceAtMost((matrix[i][j + 1] + 1)
                            .coerceAtMost(matrix[i1][j1] + (i - i1 - 1) + 1 + (j - j1 - 1)))
        }
        dA[first.indexOf(first[i - 1])] = i
    }

    for(element in swappedCharsAndCount) {
        println("These characters that were swapped ${element.key} had this many options ${swappedCharsAndCount.getValue(element.key)}")
    }
    return matrix[first.length][Second.length]
}