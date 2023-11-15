package br.kotlin.consultacepservice.studies

fun main() {
    val a = "Kotlin Null Safety"
    val b: String? = null
    println(b?.length)
    println(a?.length) // Unnecessary safe call
}