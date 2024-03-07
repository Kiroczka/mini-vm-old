package com.experimental.compilation.statements

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxType
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.StatementSyntaxType
import com.experimental.compilation.SuccessCompileResult
import com.experimental.compilation.SuccessRequireMoreCompilationResult
import com.experimental.compilation.compile

class VarDeclarationCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(\\w+)\\s+(\\w+)\\s*".toRegex()
    }

    override fun getType(): SyntaxType = StatementSyntaxType.VAR_DECLARATION
    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(matchResult: MatchResult): SuccessCompileResult {
        val type = matchResult.groupValues[1]
        val varName = matchResult.groupValues[2]
        return SuccessRequireMoreCompilationResult(
            listOf(
                CodeToCompile(ContextSyntaxType.TYPE, type),
                CodeToCompile(ContextSyntaxType.VAR_NAME, varName),
            ),
            matchResult.range.last
        )
    }

}