package com.experimental.compilation.statements

import com.experimental.compilation.CodeToCompile
import com.experimental.compilation.CompileResult
import com.experimental.compilation.Compiler
import com.experimental.compilation.ContextSyntaxElement
import com.experimental.compilation.GeneralSyntaxElement
import com.experimental.compilation.PartType
import com.experimental.compilation.StatementSyntaxElement
import com.experimental.compilation.SuccessCompileResult
import com.experimental.compilation.SuccessRequireMoreCompilationResult
import com.experimental.compilation.compile

class VarInitializationCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*(\\w+)\\s+(\\w+)\\s*=".toRegex()
    }

    override fun getType(): PartType = StatementSyntaxElement.VAR_INIT
    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(matchResult: MatchResult, code: String): SuccessCompileResult {
        val type = matchResult.groupValues[1]
        val varName = matchResult.groupValues[2]
        val startIndex = matchResult.range.last + 1
        val indexOfEndLine = code.substring(startIndex).indexOfFirst { it == '\n' }
        val endIndex = if (indexOfEndLine == -1) code.length else indexOfEndLine + startIndex
        val expression = code.substring(startIndex, endIndex)
        return SuccessRequireMoreCompilationResult(
            listOf(
                CodeToCompile(ContextSyntaxElement.TYPE, type),
                CodeToCompile(ContextSyntaxElement.VAR_NAME, varName),
                CodeToCompile(GeneralSyntaxElement.EXPRESSION, expression)
            ),
            endIndex
        )
    }

}