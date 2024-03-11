package com.experimental.compilation.expressions

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ExpressionSyntaxType
import com.experimental.compilation.FailedCompileResult
import com.experimental.compilation.GeneralSyntaxType
import com.experimental.compilation.RequireMoreCompilationResult
import com.experimental.compilation.SuccessCompileResult
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.compile

class ArithmeticExpCompiler : Compiler {
    companion object {
        private val PLUS_REGEX = "^\\s*(.+)(\\+)(.+)".toRegex()
        private val MINUS_REGEX = "^\\s*(.+)(-)(.+)".toRegex()
        private val MULTIPLY_REGEX = "^\\s*(.+)(\\*)(.+)".toRegex()
        private val REGEXES = listOf(
            PLUS_REGEX,
            MINUS_REGEX,
            MULTIPLY_REGEX
        )
    }

    override fun getType(): SyntaxType = ExpressionSyntaxType.ARITHMETIC

    override fun compile(code: String): CompileResult {
        return REGEXES
            .map { code.compile(it, this::compile) }
            .firstOrNull { it is SuccessCompileResult }
            ?: FailedCompileResult
    }


    fun compile(result: MatchResult): CompileResult {
        val firstArgument = result.groupValues[1]
        val secondArgument = result.groupValues[3]
        val operator = result.groupValues[2]

        return RequireMoreCompilationResult(
            listOf(
                CodeToCompile(GeneralSyntaxType.EXPRESSION, firstArgument),
                CodeToCompile(GeneralSyntaxType.EXPRESSION, secondArgument),
                CodeToCompile(ExpressionSyntaxType.OPERATOR, operator),
            ),
            result.range.last
        )
    }
}