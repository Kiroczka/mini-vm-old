package com.experimental.compilation.expressions

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxElement
import com.experimental.compilation.ExpressionSyntaxElement
import com.experimental.compilation.PartType
import com.experimental.compilation.SuccessCompileResult
import com.experimental.compilation.SuccessRequireMoreCompilationResult
import com.experimental.compilation.compile

class FunCallExpCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(\\w+)\\s*\\((.*(?:,.*)*)\\)".toRegex()
    }

    override fun getType(): PartType = ExpressionSyntaxElement.FUN_CALL

    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(result: MatchResult): SuccessCompileResult {
        val funName = result.groupValues[1]
        val arguments = result.groupValues[2]
        return SuccessRequireMoreCompilationResult(
            listOf(
                CodeToCompile(ContextSyntaxElement.FUN_NAME, funName),
                CodeToCompile(ExpressionSyntaxElement.FUN_CALL_ARGS, arguments),
            ),
            result.range.last
        )
    }
}

