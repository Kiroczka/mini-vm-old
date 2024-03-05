package com.experimental.compilation.expressions

import com.experimental.compilation.ConcreteExpressionCompiler
import com.experimental.compilation.ExpressionCompiler
import com.experimental.compilation.ExpressionSuccessResult
import com.experimental.components.expressions.FunctionCallExpression
import com.experimental.context.FunName
import com.experimental.exceptions.ExpressionCompilationException

class FunctionCallExpressionCompiler(
    var expressionCompiler: ExpressionCompiler? = null
) : ConcreteExpressionCompiler {
    companion object {
        internal val REGEX = "^\\s*(\\w+)\\s*\\((.*(?:,.*)*)\\)".toRegex()
        private val COMMA = ","
    }

    override fun getRegex(): Regex = REGEX
    override fun compile(matchResult: MatchResult, code: String): ExpressionSuccessResult {
        val funName = FunName(matchResult.groupValues[1])
        val arguments = matchResult.groupValues[2].split(COMMA).map {
            val result = expressionCompiler!!.compile(it)
            if (it.substring(result.lastIndex + 1).isNotBlank()) {
                throw ExpressionCompilationException("Expected comma before after expression")
            }
            result.value
        }
        return ExpressionSuccessResult(FunctionCallExpression(funName, arguments), matchResult.range.last)
    }
}

