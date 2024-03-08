package com.experimental.compilation.expressions

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxType
import com.experimental.compilation.ExpressionSyntaxType
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.SuccessCompileResult
import com.experimental.compilation.RequireMoreCompilationResult
import com.experimental.compilation.compile

class FunCallExpCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(\\w+)\\s*\\((.*(?:,.*)*)\\)".toRegex()
    }

    override fun getType(): SyntaxType = ExpressionSyntaxType.FUN_CALL

    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(result: MatchResult): SuccessCompileResult {
        val funName = result.groupValues[1]
        val arguments = result.groupValues[2]
        return RequireMoreCompilationResult(
            listOf(
                CodeToCompile(ContextSyntaxType.FUN_NAME, funName),
                CodeToCompile(ExpressionSyntaxType.FUN_CALL_ARGS, arguments),
            ),
            result.range.last
        )
    }
}

