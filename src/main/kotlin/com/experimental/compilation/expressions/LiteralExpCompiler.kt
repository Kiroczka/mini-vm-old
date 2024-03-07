package com.experimental.compilation.expressions

import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ExpressionSyntaxElement
import com.experimental.compilation.PartType
import com.experimental.compilation.compileFinal
import com.experimental.components.expressions.LiteralExpression
import com.experimental.context.IntValue
import com.experimental.context.TextValue
import com.experimental.exceptions.InternalWrongRegexException

class LiteralExpCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(\\d+)|\"(.*)\"".toRegex()
    }

    override fun getType(): PartType = ExpressionSyntaxElement.LITERAL

    override fun compile(code: String): CompileResult {
        return code.compileFinal(REGEX, this::compile)
    }

    fun compile(matchResult: MatchResult): LiteralExpression {
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
        return LiteralExpression(value)
    }
}