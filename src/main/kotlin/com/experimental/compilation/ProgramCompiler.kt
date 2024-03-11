package com.experimental.compilation

import com.experimental.components.Statement
import com.experimental.exceptions.CompilationException
import com.experimental.impl.ProgramImpl

class ProgramCompiler(private val statementCompiler: StatementCompiler) :
    Compiler {
    override fun getType(): SyntaxType = GeneralSyntaxType.PROGRAM

    override fun compile(code: String): ProgramFinalResult {
        statementCompiler.addCompiler(getType(), this)
        val statements = mutableListOf<Statement>()
        var index = 0
        while (index < code.length) {
            val codeLeft = code.substring(index)
            if (codeLeft.isBlank()) {
                break
            }
            val result = statementCompiler.compile(codeLeft)
            val statement = result.value
            if (statement is Statement) {
                index += result.lastIndex + 1
                statements.add(statement)
            } else throw CompilationException("Expected statement part but got: ${result.value}")


        }
        val programImpl = ProgramImpl(statements)
        return ProgramFinalResult(programImpl, code.length - 1)
    }
}