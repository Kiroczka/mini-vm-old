package com.experimental.compilation.statements

import com.experimental.compilation.ConcreteStatementCompiler
import com.experimental.compilation.StatementSuccessResult
import com.experimental.compilation.expressions.FunctionCallExpressionCompiler
import com.experimental.components.expressions.FunctionCallExpression
import com.experimental.components.statements.FunctionCallStatement

class FunctionCallStatementCompiler(
    private val funCallExpressionCompiler: FunctionCallExpressionCompiler,
) : ConcreteStatementCompiler {
    companion object {
        private val REGEX = "".toRegex()
    }

    override fun getRegex(): Regex = FunctionCallExpressionCompiler.REGEX

    override fun compile(matchResult: MatchResult, code: String): StatementSuccessResult {
        val result = funCallExpressionCompiler.compile(matchResult, code)
        val statement = FunctionCallStatement(result.value as FunctionCallExpression)
        return StatementSuccessResult(statement, result.lastIndex)
    }
}