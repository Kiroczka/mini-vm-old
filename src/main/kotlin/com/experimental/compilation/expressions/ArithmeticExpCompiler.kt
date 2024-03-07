package com.experimental.compilation.expressions

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ExpressionSyntaxType
import com.experimental.compilation.GeneralSyntaxType
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.SuccessRequireMoreCompilationResult
import com.experimental.compilation.compile

class ArithmeticExpCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(.*)([+\\-*])(.*)".toRegex()
    }

    override fun getType(): SyntaxType = ExpressionSyntaxType.ARITHMETIC

    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(result: MatchResult): CompileResult {
        val firstArgument = result.groupValues[1]
        val secondArgument = result.groupValues[3]
        val operator = result.groupValues[2]

        return SuccessRequireMoreCompilationResult(
            listOf(
                CodeToCompile(GeneralSyntaxType.EXPRESSION, firstArgument),
                CodeToCompile(GeneralSyntaxType.EXPRESSION, secondArgument),
                CodeToCompile(ExpressionSyntaxType.OPERATOR, operator),
            ),
            result.range.last
        )
    }
}