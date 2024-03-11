package com.experimental.utils


fun String.firstNewLineIndex(): Int {
    val indexOfEndLine = this.indexOfFirst { it == '\n' }
    return if (indexOfEndLine == -1) this.length else indexOfEndLine
}