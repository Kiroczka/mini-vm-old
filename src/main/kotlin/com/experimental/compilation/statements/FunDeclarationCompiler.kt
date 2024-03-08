package com.experimental.compilation.statements

import com.experimental.Keywords
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
import com.experimental.exceptions.InternalIncorrectRegexException

class FunDeclarationCompiler : Compiler {
    companion object {
        private val REGEX = "^\\s*fun\\s+(\\w+)\\s*\\((.*)\\)\\s*\\s*\\{((?:.|\\n)*)\\}".toRegex()
    }

    override fun getType(): SyntaxType = StatementSyntaxType.FUN_DECLARATION

    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(matchResult: MatchResult, code: String): SuccessCompileResult {
        val groupsSize = matchResult.groups.size
        if (groupsSize != 4) {
            throw InternalIncorrectRegexException()
        }
        val funName = matchResult.groupValues[1]
        val arguments = matchResult.groupValues[2]
        val bodyParts: List<CodeToCompile> = resolveBodyParts(matchResult.groupValues[3])
        val endIndex = code.indexOfFirst { it == '}' }

        val elements = listOf(
            CodeToCompile(ContextSyntaxType.FUN_NAME, funName),
            CodeToCompile(ContextSyntaxType.ARGS, arguments),
        ) + bodyParts

        return RequireMoreCompilationResult(elements, endIndex)
    }

    private fun resolveBodyParts(code: String): List<CodeToCompile> {
        val returnIndex = code.indexOf(Keywords.RETURN_KEYWORD)
        val statementsPart = resolveStatementsPart(returnIndex, code)

        if (returnIndex != -1) {
            val returnPart = resolveReturnPart(returnIndex, code)
            return listOf(statementsPart, returnPart)
        }
        return listOf(statementsPart)
    }

    private fun resolveStatementsPart(returnIndex: Int, code: String): CodeToCompile {
        val programEnd = if (returnIndex == -1) code.length else returnIndex
        val program = code.substring(0, programEnd)
        return CodeToCompile(GeneralSyntaxType.PROGRAM, program)
    }

    private fun resolveReturnPart(returnIndex: Int, code: String): CodeToCompile {
        val startReturnExpIndex = returnIndex + Keywords.RETURN_KEYWORD.length
        val returnExpression = code.substring(startReturnExpIndex)
        return CodeToCompile(GeneralSyntaxType.EXPRESSION, returnExpression)
    }

}