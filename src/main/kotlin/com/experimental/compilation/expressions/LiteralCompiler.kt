package com.experimental.compilation.expressions

import com.experimental.compilation.ConcreteExpressionCompiler
import com.experimental.compilation.ExpressionSuccessResult
import com.experimental.components.expressions.LiteralExpression
import com.experimental.context.IntValue
import com.experimental.context.TextValue
import com.experimental.exceptions.InternalWrongRegexException

class LiteralCompiler : ConcreteExpressionCompiler {
    companion object {
        private val REGEX = "^\\s*(\\d+)|\"(.*)\"".toRegex()
    }

    override fun getRegex(): Regex = REGEX

    override fun compile(matchResult: MatchResult, code: String): ExpressionSuccessResult {
        val intGroup = matchResult.groups[1]
        val textGroup = matchResult.groups[2]
        val value =
            if (intGroup != null) {
                IntValue(intGroup.value.toInt())
            } else if (textGroup != null) {
                TextValue(textGroup.value)
            } else {
                throw InternalWrongRegexException()
            }
        return ExpressionSuccessResult(LiteralExpression(value), matchResult.range.last)
    }
}