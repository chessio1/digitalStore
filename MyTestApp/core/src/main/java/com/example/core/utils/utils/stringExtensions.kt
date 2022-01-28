package com.example.core.utils.utils

fun String.convertToMoney(): String {
    return this.reversed().chunked(3)
        .joinToString(",")
        .reversed()
}