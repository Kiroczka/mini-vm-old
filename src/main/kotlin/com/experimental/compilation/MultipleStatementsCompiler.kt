package com.experimental.compilation

import com.experimental.components.Statement

class MultipleStatementsCompiler(private val compiler: StatementCompiler) {
    fun compile(code: String): List<Statement> {
        val statements = mutableListOf<Statement>()
        var index = 0
        while (index < code.length) {
            val codeLeft = code.substring(index)
            if (codeLeft.isBlank()) {
                break
            }
            val result = compiler.compile(codeLeft)
            index += result.lastIndex + 1
            statements.add(result.value)
        }
        return statements
    }
}