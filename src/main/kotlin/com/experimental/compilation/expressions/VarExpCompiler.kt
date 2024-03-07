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

class VarExpCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*\\w+".toRegex()
    }

    override fun getType(): PartType = ExpressionSyntaxElement.VAR

    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    private fun compile(matchResult: MatchResult): SuccessCompileResult {
        val varName = matchResult.value.trim()
        return SuccessRequireMoreCompilationResult(
            listOf(CodeToCompile(ContextSyntaxElement.VAR_NAME, varName)),
            matchResult.range.last
        )
    }
}