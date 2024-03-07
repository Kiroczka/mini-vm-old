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
    val type: PartType,
    val code: String
)

interface PartType

enum class GeneralSyntaxElement : PartType {
    PROGRAM,
    STATEMENT,
    EXPRESSION,
    COMMENT
}

enum class StatementSyntaxElement : PartType {
    VAR_INIT,
    VAR_DECLARATION,
    FUN_DECLARATION,
}

enum class ContextSyntaxElement : PartType {
    ARGS,
    VAR_NAME,
    FUN_NAME,
    TYPE
}

enum class ExpressionSyntaxElement : PartType {
    FUN_CALL,
    ARITHMETIC,
    OPERATOR,
    FUN_CALL_ARGS,
    VAR,
    LITERAL
}