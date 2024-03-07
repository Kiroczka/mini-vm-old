package com.experimental.compilation.expressions

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ExpressionSyntaxElement
import com.experimental.compilation.GeneralSyntaxElement
import com.experimental.compilation.PartType
import com.experimental.compilation.SuccessRequireMoreCompilationResult
import com.experimental.compilation.compile

class ArithmeticExpCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(.*)([+\\-*])(.*)".toRegex()
    }

    override fun getType(): PartType = ExpressionSyntaxElement.ARITHMETIC

    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(result: MatchResult): CompileResult {
        val firstArgument = result.groupValues[1]
        val secondArgument = result.groupValues[3]
        val operator = result.groupValues[2]

        return SuccessRequireMoreCompilationResult(
            listOf(
                CodeToCompile(GeneralSyntaxElement.EXPRESSION, firstArgument),
                CodeToCompile(GeneralSyntaxElement.EXPRESSION, secondArgument),
                CodeToCompile(ExpressionSyntaxElement.OPERATOR, operator),
            ),
            result.range.last
        )
    }
}