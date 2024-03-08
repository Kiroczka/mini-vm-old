package com.experimental.compilation.statements

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxType
import com.experimental.compilation.GeneralSyntaxType
import com.experimental.compilation.StatementSyntaxType
import com.experimental.compilation.SuccessCompileResult
import com.experimental.compilation.RequireMoreCompilationResult
import com.experimental.compilation.SyntaxType
import com.experimental.compilation.compile
import com.experimental.utils.firstNewLineIndex

class VarInitializationCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(\\w+)\\s+(\\w+)\\s*=".toRegex()
    }

    override fun getType(): SyntaxType = StatementSyntaxType.VAR_INIT
    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(matchResult: MatchResult, code: String): SuccessCompileResult {
        val type = matchResult.groupValues[1]
        val varName = matchResult.groupValues[2]
        val startIndex = matchResult.range.last + 1
        val endIndex = startIndex + code.substring(startIndex).firstNewLineIndex()
        val expression = code.substring(startIndex, endIndex)
        return RequireMoreCompilationResult(
            listOf(
                CodeToCompile(ContextSyntaxType.TYPE, type),
                CodeToCompile(ContextSyntaxType.VAR_NAME, varName),
                CodeToCompile(GeneralSyntaxType.EXPRESSION, expression)
            ),
            endIndex
        )
    }

}