package com.example.demo.view

import com.example.demo.dataclass.getDHSimilarity
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import tornadofx.*
import kotlin.system.measureTimeMillis

class Name_Check : View("Name Check") {
    override val root = vbox {
        var firstNameField: TextField by singleAssign()
        var secondNameField: TextField by singleAssign()
        var calculateSwappedCount: CheckBox by singleAssign()

        hbox {
            label("Calculate Swapped Count")
            calculateSwappedCount = checkbox()
        }

        hbox {
            label("First Name")
            firstNameField = textfield()
        }
        hbox {
            label("Second Name")
            secondNameField = textfield()
        }
        button("Compare!") {
            useMaxWidth = true
            action {
                val time = measureTimeMillis {
                    println("\nComparing: ${firstNameField.text} and ${secondNameField.text}")
                    println("The Damerau Levenshtein value for ${firstNameField.text} ${secondNameField.text} is: ${getDHSimilarity(firstNameField.text, secondNameField.text, calculateSwappedCount.isSelected)}")
                }
                printTimeItTook(time)

            }
        }
        button("Compare without case!") {
            useMaxWidth = true
            action {
                val time = measureTimeMillis {
                    println("\nComparing without case: ${firstNameField.text} and ${secondNameField.text}")
                    println("The Damerau Levenshtein value for ${firstNameField.text} ${secondNameField.text} is: ${getDHSimilarity(firstNameField.text.toUpperCase()
                            , secondNameField.text.toUpperCase(), calculateSwappedCount.isSelected)}")
                }
                printTimeItTook(time)
            }
        }
        button("Compare without case or spaces!") {
            useMaxWidth = true
            action {
                val time = measureTimeMillis {
                    println("\nComparing without case or spaces: ${firstNameField.text} and ${secondNameField.text}")
                    println("The Damerau Levenshtein value for ${firstNameField.text.replace("\\s".toRegex(), "")}" +
                            " ${secondNameField.text.replace("\\s".toRegex(), "")} is: ${getDHSimilarity(firstNameField.text.toUpperCase().replace("\\s".toRegex(), "")
                                    , secondNameField.text.toUpperCase().replace("\\s".toRegex(), ""), calculateSwappedCount.isSelected)}")
                }
                printTimeItTook(time)

            }
        }
    }
}

fun printTimeItTook(time: Long) {
    if (time < 1000) {
        println("The comparison took $time ms")
    } else {
        println("The comparison took ${time / 1000}.${time % 1000} seconds")
    }
}
