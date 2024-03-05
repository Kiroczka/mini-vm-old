package com.experimental.compilation.statements

import com.experimental.compilation.ConcreteStatementCompiler
import com.experimental.compilation.ExpressionCompiler
import com.experimental.compilation.StatementSuccessResult
import com.experimental.compilation.TypeCompiler
import com.experimental.components.statements.VarDeclaration
import com.experimental.context.VarName

class VarDeclarationCompiler(
    private val expressionCompiler: ExpressionCompiler,
    private val typeCompiler: TypeCompiler
) : ConcreteStatementCompiler {
    companion object {
        private val REGEX = "^\\s*(var|int|text)\\s+(\\w+)\\s*=".toRegex()
    }

    override fun compile(matchResult: MatchResult, code: String): StatementSuccessResult {
        val type = typeCompiler.compile(matchResult.groupValues[1])
        //TODO parser?
        val varName = VarName(matchResult.groupValues[2])
        val startIndex = matchResult.range.last + 1
        val indexOfEndLine = code.substring(startIndex).indexOfFirst { it == '\n' }
        val endIndex = if (indexOfEndLine == -1) code.length else indexOfEndLine + startIndex
        val expression = expressionCompiler.compile(code.substring(startIndex, endIndex)).value
        val varDeclaration = VarDeclaration(varName, type, expression)
        return StatementSuccessResult(varDeclaration, endIndex)
    }

    override fun getRegex(): Regex = REGEX


}