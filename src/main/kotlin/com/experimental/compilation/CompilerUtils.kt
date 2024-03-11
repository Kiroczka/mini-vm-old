package com.experimental.compilation


inline fun String.compile(
    regex: Regex,
    compile: (MatchResult, String) -> CompileResult
): CompileResult {
    return when (val result = regex.find(this)) {
        null -> FailedCompileResult
        else -> compile(result, this)
    }
}

inline fun String.compile(
    regex: Regex,
    compile: (MatchResult) -> CompileResult
): CompileResult {
    return when (val result = regex.find(this)) {
        null -> FailedCompileResult
        else -> compile(result)
    }
}

inline fun String.compileFinal(
    regex: Regex,
    compile: (MatchResult) -> SyntaxElement
): CompileResult {
    return when (val result = regex.find(this)) {
        null -> FailedCompileResult
        else -> SuccessFinalResult(compile(result), result.range.last)
    }
}
