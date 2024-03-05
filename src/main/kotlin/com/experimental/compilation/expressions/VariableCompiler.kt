package com.experimental.compilation.expressions

import com.experimental.compilation.ConcreteExpressionCompiler
import com.experimental.compilation.ExpressionSuccessResult
import com.experimental.components.expressions.VariableExpression
import com.experimental.context.VarName

class VariableCompiler : ConcreteExpressionCompiler {
    companion object {
        private val REGEX = "^\\s*\\w+".toRegex()
    }

    override fun getRegex(): Regex = REGEX

    override fun compile(matchResult: MatchResult, code: String): ExpressionSuccessResult {
        val varName = matchResult.value.trim()
        return ExpressionSuccessResult(VariableExpression(VarName(varName)), matchResult.range.last)
    }
}