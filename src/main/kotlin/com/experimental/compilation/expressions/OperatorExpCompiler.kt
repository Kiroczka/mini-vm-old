package com.experimental.compilation.expressions

import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ExpressionSyntaxElement
import com.experimental.compilation.PartType
import com.experimental.compilation.compileFinal
import com.experimental.components.expressions.MinusOperator
import com.experimental.components.expressions.MultiplyOperator
import com.experimental.components.expressions.Operator
import com.experimental.components.expressions.PlusOperator

class OperatorExpCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*([+\\-*])".toRegex()
    }

    override fun compile(code: String): CompileResult {
        return code.compileFinal(REGEX, this::compile)
    }

    fun compile(result: MatchResult): Operator {
        return when (val rawOperator = result.groupValues[1]) {
            "+" -> PlusOperator
            "-" -> MinusOperator
            "*" -> MultiplyOperator
            else -> throw ArithmeticException("Unknown operator: $rawOperator")
        }
    }

    override fun getType(): PartType = ExpressionSyntaxElement.OPERATOR

}