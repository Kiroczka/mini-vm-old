package com.experimental.utils


fun String.firstNonEmptyLine(): String {
    return this.split("\n").find { it.isNotEmpty() } ?: ""
}