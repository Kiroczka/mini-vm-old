package com.experimental.compilation

interface Compiler {
    fun getType(): SyntaxType

    fun compile(code: String): CompileResult

}
