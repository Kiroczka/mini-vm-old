package com.experimental.compilation

import com.experimental.components.Component
import com.experimental.components.Expression
import com.experimental.components.Statement

interface ConcreteComponentCompiler<T : Component> {
    companion object {
        private val REGEX_START = "^\\s*".toRegex()
    }

    fun compile(code: String): Result<T> {
        return when (val result = getRegex().find(code)) {
            null -> createFailedResult()
            else -> compile(result, code)
        }
    }

    fun getRegex(): Regex
    fun compile(matchResult: MatchResult, code: String): SuccessResult<T>
    fun createFailedResult(): FailedResult<T>
}

interface ConcreteExpressionCompiler : ConcreteComponentCompiler<Expression> {
    override fun createFailedResult(): FailedResult<Expression> = ExpressionFailedResult
}

interface ConcreteStatementCompiler : ConcreteComponentCompiler<Statement> {
    override fun createFailedResult(): FailedResult<Statement> = StatementFailedResult

}
