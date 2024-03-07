package com.experimental.compilation.statements

import com.experimental.Keywords
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
import com.experimental.exceptions.InternalWrongRegexException

class FunDeclarationCompiler : Compiler {
    companion object {
        private val REGEX =
            "^\\s*^fun\\s+(\\w+)\\s*\\(((?:\\w+\\s+\\w+)?\\s*(?:,\\s*\\w+\\s+\\w+\\s*)*)\\)\\s*\\s*\\{((?:.|\\n)*)\\}".toRegex()
    }


    override fun getType(): PartType = StatementSyntaxElement.FUN_DECLARATION

    override fun compile(code: String): CompileResult {
        return code.compile(REGEX, this::compile)
    }

    fun compile(matchResult: MatchResult, code: String): SuccessCompileResult {
        val groupsSize = matchResult.groupValues.size
        if (groupsSize != 4) {
            throw InternalWrongRegexException()
        }
        val funName = matchResult.groupValues[1]
        val arguments = matchResult.groupValues[2]
        val bodyStart = code.indexOfFirst { it == '{' } + 1
        val endIndex = code.indexOfFirst { it == '}' }
        val body = code.substring(bodyStart, endIndex)
        val returnIndex = code.indexOf(Keywords.RETURN_KEYWORD)
        val programEnd = if (returnIndex == -1) endIndex else returnIndex
        val elements = mutableListOf(
            CodeToCompile(ContextSyntaxElement.FUN_NAME, funName),
            CodeToCompile(ContextSyntaxElement.ARGS, arguments),
            CodeToCompile(GeneralSyntaxElement.PROGRAM, code.substring(matchResult.groups[3]!!.range.first, programEnd)),
        )
        if (returnIndex != -1) {
            val returnExpression = body.substring(returnIndex + Keywords.RETURN_KEYWORD.length)
            elements.add(CodeToCompile(GeneralSyntaxElement.EXPRESSION, returnExpression))
        }
        return SuccessRequireMoreCompilationResult(elements, endIndex + 1)
    }

}