package com.experimental.compilation.statements

import com.experimental.Keywords
import com.experimental.compilation.ConcreteStatementCompiler
import com.experimental.compilation.ExpressionCompiler
import com.experimental.compilation.MultipleStatementsCompiler
import com.experimental.compilation.StatementSuccessResult
import com.experimental.compilation.TypeCompiler
import com.experimental.components.Expression
import com.experimental.components.statements.FunDeclaration
import com.experimental.context.Argument
import com.experimental.context.FunName
import com.experimental.context.Function
import com.experimental.context.VarName
import com.experimental.exceptions.FunctionCompilationException
import com.experimental.exceptions.InternalWrongRegexException

class FunDeclarationCompiler(
    var statementCompiler: MultipleStatementsCompiler?,
    private val expressionCompiler: ExpressionCompiler,
    private val typeCompiler: TypeCompiler
) :
    ConcreteStatementCompiler {
    companion object {
        private val REGEX =
            "^\\s*^fun\\s+(\\w+)\\s*\\(((?:\\w+\\s+\\w+)?\\s*(?:,\\s*\\w+\\s+\\w+\\s*)*)\\)\\s*\\s*\\{((?:.|\\n)*)\\}".toRegex()
        private val ARGUMENTS_REGEX = "\\s*(\\w+)\\s+(\\w+)\\s*".toRegex()
    }

    override fun getRegex(): Regex = REGEX

    override fun compile(matchResult: MatchResult, code: String): StatementSuccessResult {
        val groupsSize = matchResult.groupValues.size
        if (groupsSize != 4) {
            throw InternalWrongRegexException()
        }
        val funName = FunName(matchResult.groupValues[1])
        val arguments = compileArguments(matchResult.groupValues[2])
        val theRest = matchResult.groupValues[3]
        val endIndex = theRest.indexOfFirst { it == '}' }
        val body = theRest.substring(0, endIndex)
        val returnIndex = body.indexOf(Keywords.RETURN_KEYWORD)
        val expression = resolveReturnExpression(body, returnIndex)
        val statements = statementCompiler!!.compile(body.substring(0, returnIndex))
        val funDeclaration = FunDeclaration(Function(funName, arguments, statements, expression))
        return StatementSuccessResult(funDeclaration, matchResult.range.last)
    }

    private fun resolveReturnExpression(code: String, returnIndex: Int): Expression? {
        val expression = if (returnIndex == -1) {
            null
        } else {
            val startIndex = returnIndex + Keywords.RETURN_KEYWORD.length
            val result = expressionCompiler.compile(code.substring(startIndex))
            if (code.substring(startIndex + result.lastIndex + 1).isNotBlank()) {
                throw FunctionCompilationException("There should be nothing after return expression")
            }
            result.value
        }
        return expression
    }

    private fun compileArguments(code: String): List<Argument> {
        return ARGUMENTS_REGEX.findAll(code).map {
            val type = typeCompiler.compile(it.groupValues[1])
            val varName = VarName(it.groupValues[2])
            Argument(varName, type)
        }.toList()
    }


}