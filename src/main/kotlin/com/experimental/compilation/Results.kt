package com.experimental.compilation

sealed interface CompileResult
data object FailedCompileResult : CompileResult
sealed interface SuccessCompileResult : CompileResult
class SuccessRequireMoreCompilationResult(
    val codeParts: List<CodeToCompile>,
    val lastIndex: Int
) : SuccessCompileResult

class SuccessFinalResult(
    val value: SyntaxElement,
    val lastIndex: Int
) : SuccessCompileResult

interface SyntaxElement
class CodeToCompile(
    val type: SyntaxType,
    val code: String
)

interface SyntaxType

enum class GeneralSyntaxType : SyntaxType {
    PROGRAM,
    STATEMENT,
    EXPRESSION,
    COMMENT
}

enum class StatementSyntaxType : SyntaxType {
    VAR_INIT,
    VAR_DECLARATION,
    FUN_DECLARATION,
}

enum class ContextSyntaxType : SyntaxType {
    ARGS,
    VAR_NAME,
    FUN_NAME,
    TYPE
}

enum class ExpressionSyntaxType : SyntaxType {
    FUN_CALL,
    ARITHMETIC,
    OPERATOR,
    FUN_CALL_ARGS,
    VAR,
    LITERAL
}