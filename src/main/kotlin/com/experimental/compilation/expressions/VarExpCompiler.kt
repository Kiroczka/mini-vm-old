package com.experimental.compilation.expressions

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxType
import com.experimental.compilation.ExpressionSyntaxType
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.SuccessCompileResult
import com.experimental.compilation.SuccessRequireMoreCompilationResult
import com.experimental.compilation.compile

class VarExpCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*\\w+".toRegex()
    }

    override fun getType(): SyntaxType = ExpressionSyntaxType.VAR

    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    private fun compile(matchResult: MatchResult): SuccessCompileResult {
        val varName = matchResult.value.trim()
        return SuccessRequireMoreCompilationResult(
            listOf(CodeToCompile(ContextSyntaxType.VAR_NAME, varName)),
            matchResult.range.last
        )
    }
}