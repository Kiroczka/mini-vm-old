package com.experimental.compilation.expressions

import com.experimental.compilation.ConcreteExpressionCompiler
import com.experimental.compilation.ExpressionCompiler
import com.experimental.compilation.ExpressionSuccessResult
import com.experimental.components.expressions.MinusExpression
import com.experimental.components.expressions.MultiplyExpression
import com.experimental.components.expressions.PlusExpression
import com.experimental.exceptions.ArithmeticException

class ArithmeticCompiler(var expressionCompiler: ExpressionCompiler? = null) : ConcreteExpressionCompiler {
    companion object {
        private val REGEX = "^\\s*(.*)(\\+|-|\\*)(.*)".toRegex()
    }

    override fun getRegex(): Regex = REGEX

    override fun compile(matchResult: MatchResult, code: String): ExpressionSuccessResult {
        val first = expressionCompiler!!.compile(matchResult.groupValues[1]).value
        val second = expressionCompiler!!.compile(matchResult.groupValues[3]).value
        val expression = when (val operator = matchResult.groupValues[2]) {
            "+" -> PlusExpression(first, second)
            "-" -> MinusExpression(first, second)
            "*" -> MultiplyExpression(first, second)
            else -> throw ArithmeticException("Unknown operator: $operator")
        }
        return ExpressionSuccessResult(expression, matchResult.range.last)
    }
}